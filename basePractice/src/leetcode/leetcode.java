package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class leetcode {
    int max =Integer.MAX_VALUE;
    public int minCut(String s) {
        
            ArrayList<String> list = new ArrayList<>();
            int len = s.length();
            if(len==1){
               
               return max;
            }
    
            int start = 0;
            dfs(s,0,list);
            return max;
    }
     public void dfs(String s,int start,ArrayList<String> list) {
            if(start==s.length()) {
                int size = list.size()-1;
                max = (max<=size)?max:size;
                return ;
            }
            for(int i=start+1;i<=s.length();i++) {
                String record = s.substring(start,i);
                if(checkPalidrome(record)) {
                    list.add(record);
                    dfs(s,i,list);
                    list.remove(list.size()-1);
                }
            }
            
        }
        boolean checkPalidrome(String s){
            int start =0;
            int end=s.length()-1;
            while(start<end) {
                if(s.charAt(start)==s.charAt(end)){
                    start++;
                    end--;
                }else{
                    return false;
                }
            } return true;
        }
   public static void main(String[] args) {
	leetcode let =new leetcode();
//	int[][]nums = {{1,3,5}};
//		{{1,4,7,11,15},
//		 {2,5,8,12,19},
//		 {3,6,9,16,22},
//		 {10,13,14,17,24},
//		 {18,21,23,26,30}};
	System.out.println(let.minCut("sshcdjaj"));
	return ;
}

}
