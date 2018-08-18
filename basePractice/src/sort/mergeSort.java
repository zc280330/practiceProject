package sort;

import java.util.Arrays;

public class mergeSort {
	public static void main(String []args){
        int []arr = {9,8,7,6,5,4,3,2,1};
        int []temp = new int[arr.length];
        sort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

	private static void sort(int[] arr, int left, int right, int[] temp) {
		if(left<right){
			int middle = (left+right)/2;
			sort(arr, left, middle, temp);
			sort(arr, middle+1, right, temp);
			mergeSort(arr,left,middle,right,temp);
		}
		
	}

	private static void mergeSort(int[] arr, int left, int middle, int right,
			int[] temp) {
		int i = left;
		int j= middle+1;
		int k=left;
		while(i<=middle&&j<=right){
			if(arr[i]<=arr[j]){
				temp[k++]=arr[i++];
			}else{
				temp[k++]=arr[j++];
			}
		}
		while(i<=middle){
			temp[k++]=arr[i++];
		}
		while(j<=right){
			temp[k++]=arr[j++];
		} 
		k=left;
		while(left<=right){
			arr[left++]=temp[k++];
		}
		
	}

	
   

}
