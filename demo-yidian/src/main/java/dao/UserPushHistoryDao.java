package dao;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.yidian.data.push.keep.history.core.HistoryHBaseDecoder;
import com.yidian.data.push.keep.history.core.HistoryHBaseEncoder;
import com.yidian.data.push.keep.proto.CommonProtos;
import com.yidian.data.push.keep.proto.HistoryProtos;
import data.AppidPair;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import yidian.data.neo.store.HBaseClient;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Slf4j
public class UserPushHistoryDao {
    private HistoryHBaseEncoder encoder = new HistoryHBaseEncoder();
    private HistoryHBaseDecoder decoder = new HistoryHBaseDecoder();

    private HBaseClient hbaseClient;
    private HTableInterface htable;

    public UserPushHistoryDao() throws IOException {
        this("push_user_history", "l-push-keep1.serving.prod.bj3.yidian-inc.com,l-push-keep1.serving.prod.bj1.yidian-inc.com", 2181);
    }

    public UserPushHistoryDao(String table, String quorum, int port) throws IOException {
        Configuration hbaseConf = HBaseConfiguration.create();
        hbaseConf.clear();
        hbaseConf.set("hbase.zookeeper.quorum", quorum);
        hbaseConf.setInt("hbase.zookeeper.property.clientPort", port);
        hbaseClient = new HBaseClient(hbaseConf, 256);
        htable = hbaseClient.newTable(TableName.valueOf(table));
    }

    public void close (){
        if (hbaseClient != null) {
            hbaseClient.close();
        }
    }

    public List<HistoryProtos.UserHistory> getUsersHistory(List<CommonProtos.UserIdentifier> users) throws IOException {

        List<Get> gets = users.stream().map(encoder::encodeToGet).collect(Collectors.toList());
        Result[] results = htable.get(gets);
        return Arrays.stream(results)
                .filter(Objects::nonNull)
                .filter(r -> r.getRow() != null)
                .map(h -> {
                    try {
                        return decoder.decode(h, null);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static List<CommonProtos.UserIdentifier> convert2UserIdentifier(long userid, Map<String, Set<AppidPair>> platform2appid) {
        List<CommonProtos.UserIdentifier> userIdentifiers = Lists.newLinkedList();

        for (Map.Entry<String, Set<AppidPair>> entry: platform2appid.entrySet()) {
            CommonProtos.UserIdentifier.Platform platform = str2Platform(entry.getKey());
            for (AppidPair appidPair: entry.getValue()) {
                CommonProtos.UserIdentifier userIdentifier = CommonProtos.UserIdentifier
                        .newBuilder()
                        .setUserId(userid)
                        .setPlatform(platform)
                        .setAppId(appidPair.getAppid())
                        .build();
                userIdentifiers.add(userIdentifier);
            }
        }

        return userIdentifiers;
    }

    private static CommonProtos.UserIdentifier.Platform str2Platform(String platform) {
        String PLATFORM = platform.toUpperCase();
        if ("IPHONE".equals(PLATFORM) || "IOS".equals(PLATFORM)) {
            return CommonProtos.UserIdentifier.Platform.IOS;
        }

        return CommonProtos.UserIdentifier.Platform.ANDROID;
    }

    public static Map<Long, Set<String>> userid2Docids(List<HistoryProtos.UserHistory> histories) {
        Map<Long, Set<String>> user2docs = Maps.newHashMap();
        Pattern p = Pattern.compile("^[a-zA-Z]_[A-Za-z0-9_,]+");
        for (HistoryProtos.UserHistory history: histories) {
            List<String> docids = history.getHistoriesList()
                    .stream()
                    .map(HistoryProtos.History::getResourceId)//todo: C_docid,docid,docid; K_docid,docid,docid这2种类型的需要拆开来。。拆成独立的docid  //参考正则："^[a-zA-Z]_[A-Za-z0-9_,]+"
                    .collect(Collectors.toList());

            //拆分
            List<String> splitList = new ArrayList<>();
            Iterator<String> it = docids.iterator();
            while(it.hasNext()){
                String docid = it.next();
                Matcher m = p.matcher(docid);
                if(m.matches() && docid.length()>2){
                    it.remove();
                    String docidNew = docid.substring(2, docid.length());
                    String[] docidArr = docidNew.split(",");
                    for(String cur : docidArr){
                        splitList.add(cur);
                    }
                }
            }
            docids.addAll(splitList);

            user2docs.computeIfAbsent(history.getUserIdentifier().getUserId(), k -> Sets.newHashSet()).addAll(docids);
        }

        return user2docs;
    }

    public static void main(String[] args) throws IOException {
        long uid = 714958022;
        UserPushHistoryDao userPushHistoryDao = new UserPushHistoryDao();
        Map<String, Set<AppidPair>> platform2appid = Maps.newHashMap();
        platform2appid.put("ANDROID", Sets.newHashSet(new AppidPair("yidian","yidian")));
        List<CommonProtos.UserIdentifier> userIdentifiers = convert2UserIdentifier(uid, platform2appid);

        List<HistoryProtos.UserHistory> histories = userPushHistoryDao.getUsersHistory(userIdentifiers);
        Map<Long, Set<String>> uid2docids = userid2Docids(histories);
        for (Map.Entry<Long, Set<String>> u2d: uid2docids.entrySet()) {
            System.out.println(u2d.getKey() + ": " + Joiner.on(",").join(u2d.getValue()));
        }

    }
}
