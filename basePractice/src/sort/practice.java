package sort;

import java.util.Arrays;

public class practice {
	public static void main(String []args) {
        int []arr = {9,8,7,6,5,4,3,2,1};
        int []temp = new int[arr.length];
        sort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }
	public static void sort(int[]arr,int left,int right,int[]temp){
		if(left<right){
			int middle = (left+right)/2;
			sort(arr, left, middle, temp);
			sort(arr, middle+1, right, temp);
			merge(arr,left,middle,right,temp);
		}
	}
	private static void merge(int[] arr, int left, int middle, int right,
			int[] temp) {
		int j = left;
		int k = middle+1;
		int i = left;
		while(j<=middle&&k<=right){
			if(arr[j]<arr[k]) {
				temp[i++]=arr[j++];
			}else if(arr[j]>=arr[k]){
				temp[i++]=arr[k++];
			}
		}
		while(j<=middle){
			temp[i++]=arr[j++];
		}
		while(k<=right){
			temp[i++]=arr[k++];
		}
		i = left;
		while(left<=right){
			arr[left++]=temp[i++];
		}
	}
}
