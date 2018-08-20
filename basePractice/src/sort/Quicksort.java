package sort;

public class Quicksort {

    private static int[] a = {2,8,3,9,4,1,7,4,8,3,8,6,7};
    public static void main(String[] args) {
        int left =0 ;int right =a.length-1;
        sort(a,left,right);
        System.out.print(a);

    }

    private static void sort(int[] a, int left, int right) {
        if(left<right){
            int pivot = quickSort(a,left,right);
            sort(a,left,pivot);
            sort(a,pivot+1,right);
        }

    }

    public static int quickSort(int[] a,int left, int right){
        int pivot = a[left];
       while(left<right){
           while(left<right&&a[right]>=pivot) right--;
           a[left]=a[right];
           while(left<right&&a[left]<=pivot) left++;
           a[right]=a[left];


       }
        a[left]=pivot;
       return left;
    }

}
