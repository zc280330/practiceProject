package jianZhiOffer;

import java.util.ArrayList;

public class FindContinuousSequence {



    public static ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        FindContinuousSequence so = new FindContinuousSequence();
        result = FindContinuousSequence.FindContinuousSequence(5);
        System.out.print(result);

    }

    public static ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        if(sum<=1) return result;
        int left = 1;
        int right =1;
        int count = 1;
        while(left<=right&&right<=sum/2+1){
            if(count==sum) {
                addResult(left,right);
                right++;
                count+=right;
            }
            else if(count<sum){
                right++;
                count+=right;


            }else{

                count-=left;
                left++;


            }
        }
        return result;
    }
    public static void addResult(int left,int right){
        ArrayList<Integer> current = new ArrayList<>();
        for(int i =left;i<=right;i++){
            current.add(i);
        }
        result.add(current);
    }
}
