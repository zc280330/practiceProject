package sort;

import java.util.ArrayList;

public class topK {
	
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        int len = input.length;
      
        for(int i=len/2-1;i>=0;i--){
            adjust(input,i,len);
        }
        for(int i=len-1;i>=0;i--){
            int temp =input[i];
            input[i]=input[0];
            input[0]=temp;
            adjust(input,0,i);
        }
      
         ArrayList<Integer> result = new ArrayList<>();
        for(int i= 0;i<k;i++){
            result.add(input[i]);
        }
        return result;
    }
    public void adjust(int[] input,int i,int len){
        int j = 2*i+1; //leftChild
        while(j<len) {
           if(j+1<len&&input[j+1]>input[j]){
                j++;
            }
            if(input[i]>input[j]) break;
            int temp = input[i];
            input[i] = input[j];
            input[j] =temp;
            i=j;
            j=2*i+1;
        }
    }

    public static void main(String[] args) {
    	topK TopK = new topK();
    	int [] input={4,5,1,6,2,7,3,8};
		ArrayList<Integer> list =TopK.GetLeastNumbers_Solution(input, 4) ;
		System.out.print(list);
	}
}
