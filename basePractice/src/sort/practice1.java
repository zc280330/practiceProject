package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
public class practice1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = Integer.parseInt(in.nextLine()); 
        List<Interval> interList = new ArrayList<>();
        for(int i = 0;i<m;i++){
        	String[] editor = in.nextLine().split(";");
            for(String s :editor){
            	String[] interStr = s.split(",");
            	int start = Integer.parseInt(interStr[0]);
            	int end = Integer.parseInt(interStr[1]);
            	Interval newInterval = new Interval(start,end);
            	interList.add(new Interval(start,end));
            }
        }
        
       interList = merge(interList);
       
       for(int i=0;i<interList.size();i++){
    	   Interval it = interList.get(i);
    	   if(i!=interList.size()){
    		   
    		   System.out.print(it.start+","+it.end+";"); 
    	   }else System.out.print(it.start+","+it.end); 
    	   
       }

        
    }
    
    
    public static class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
     }
   
        public static List<Interval> merge(List<Interval> intervals) 
        {
            List<Interval> res = new ArrayList<>();
            if(intervals.size() == 0)
                return res;
            
            // sort the intervals list according to start
            Collections.sort(intervals, new Comparator<Interval>(){
                public int compare(Interval a, Interval b)
                {
                    return a.start - b.start;
                }
            });;
            
            // get first one
            Interval temp = intervals.get(0);
            
            // if intervals only has one element
            if(intervals.size() == 1)
            {
                res.add(temp);
                return res;
            }
            
            // iterate intervals from [1] to end
            for(int i=1; i<intervals.size(); i++)
            {
                // case 1: if temp interval end >= this interval start -> 
                //            merge tempStart, max(tempEnd, thisEnd) and make this merge one as new temp;
                if(temp.end >= intervals.get(i).start)
                {
                    temp.end = Math.max(temp.end, intervals.get(i).end);
                }
                // case 2: if temp interval is not overlapping this interval ->
                //            add temp into list and make this new temp
                else
                {
                    res.add(temp);
                    temp = intervals.get(i);
                }
            }
            // add the last temp into list
            res.add(temp);
            
    
            return res;
        }
    
  
}