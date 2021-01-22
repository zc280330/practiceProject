package sort;

import java.util.*;

public class topKFrequecy {
    class Pair{
        int num;
        int count;
        public Pair(int num, int count){
            this.num=num;
            this.count=count;
        }
    }

    public class Solution {
        /*
        方法一：利用优先队列，关键是自定义比较器
         */
        public List<Integer> topKFrequent1(int[] nums, int k) {
            //count the frequency for each element
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int num: nums){
                if(map.containsKey(num)){
                    map.put(num, map.get(num)+1);
                }else{
                    map.put(num, 1);
                }
            }

            // create a min heap
            PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>(){
                public int compare(Pair a, Pair b){
                    return a.count-b.count;
                }
            });

            //maintain a heap of size k.
            for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                Pair p = new Pair(entry.getKey(), entry.getValue());
                queue.offer(p);
                if(queue.size()>k){
                    queue.poll();
                }
            }

            //get all elements from the heap
            List<Integer> result = new ArrayList<Integer>();
            while(queue.size()>0){
                result.add(queue.poll().num);
            }
            //reverse the order
            Collections.reverse(result);

            return result;
        }
    /*
    方法二：桶排序。
     */
        public List<Integer> topKFrequent2(int[] nums, int k) {
            //count the frequency for each element
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int num: nums){
                if(map.containsKey(num)){
                    map.put(num, map.get(num)+1);
                }else{
                    map.put(num, 1);
                }
            }

            //get the max frequency
            int max = 0;
            for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                max = Math.max(max, entry.getValue());
            }

            //initialize an array of ArrayList. index is frequency, value is list of numbers
            ArrayList<Integer>[] arr = (ArrayList<Integer>[]) new ArrayList[max+1];
            for(int i=1; i<=max; i++){
                arr[i]=new ArrayList<Integer>();
            }

            for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                int count = entry.getValue();
                int number = entry.getKey();
                arr[count].add(number);
            }

            List<Integer> result = new ArrayList<Integer>();

            //add most frequent numbers to result
            for(int j=max; j>=1; j--){
                if(arr[j].size()>0){
                    for(int a: arr[j]){
                        result.add(a);
                        //if size==k, stop
                        if(result.size()==k){
                            break;
                        }
                    }
                }
            }

            return result;
        }
    }
}
