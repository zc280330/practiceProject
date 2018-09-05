package jianZhiOffer;

public class minNumberInRotateArray {

    public static void main(String[] args) {

        int[] input = {1,0,1,1,1};
        int result = minNumberInRotateArray(input);
        System.out.print(result);

    }

    public static int minNumberInRotateArray(int[] array) {
        if (array.length == 0)
            return 0;
        int left = 0;
        int right = array.length - 1;
        int middle = 0;
        while (array[left]>=array[right]) {
            if(right-left==1){
                middle = right;
                break;
            }
            middle = left + (right - left) / 2;
            if (array[middle] >= array[left]) {
                left = middle;
            }
            if (array[middle] <= array[right]) {
                right = middle;
            }
        }
        return array[middle];
    }


}
