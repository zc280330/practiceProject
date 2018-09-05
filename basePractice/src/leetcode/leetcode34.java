package leetcode;

public class leetcode34 {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        int low =0 ; int high =nums.length-1; int pos =-1; int mid =0;
        int left = -1; int right = -1;
        while(low<=high) {
            mid =low +(high-low)/2;
            if(nums[mid] ==target) {
                pos=mid;
                break;
            } else if (nums[mid]<target){
                low = mid+1 ;
            } else {
                high = mid-1;
            }
        }
        if(pos==-1) { result[0] = pos;result[1]=pos; return result;}
        low =0;high=mid;
        while(low<=high) {
            mid = low +(high-low)/2;
            if(nums[mid]==target) {
                if(mid==0) { left = mid; break;}
                else if(nums[mid]!=nums[mid-1]){
                    left=mid; break;
                }else{
                    high=mid-1;
                }
            }else{
                low=mid+1;
            }
        }
        low =mid;high=nums.length-1;
        while(low<=high) {
            mid = low +(high-low)/2;
            if(nums[mid]==target) {
                if(mid==nums.length-1) { right = mid; break;}
                else if(nums[mid]!=nums[mid+1]){
                    right=mid; break;
                }else{
                    low=mid+1;
                }
            }else{
                high=mid-1;
            }
        }
        result[0]=left;
        result[1]=right;
        return result;
    }

    public static void main(String[] args) {

        int[] input = {5,7,7,8,8,10};
        int [] result = searchRange(input,8);
        System.out.print(result[0]+"   "+result[1]);

    }
}
