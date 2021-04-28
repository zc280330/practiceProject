package data;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
public class AppidPair {

    public String appid;
    public String realAppid;
    public AppidPair(String appid, String realAppid){
        this.appid = appid;
        this.realAppid = realAppid;
    }
    public String toString(){
        return appid+"\t"+realAppid;
    }
    public static void main(String[] args) {
        Set<AppidPair> pairSet = new HashSet<>();
        pairSet.add(new AppidPair("a","b"));
        pairSet.add(new AppidPair("a","b"));
        pairSet.add(new AppidPair("a","b"));
        System.out.println(pairSet.size());
    }

}
