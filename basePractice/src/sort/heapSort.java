package sort;

public class heapSort {
	private static int[] a = {2,8,3,9,4,1,7,4,8,3,8,6,7};
	public static void main(String[] args) {
		
		 makeHeap(a,a.length);
		 for(int i =a.length-1;i>=0;i--){
			 int temp  = a[0];
			 a[0] = a[i];
			 a[i]=temp;
			 adjust(a, 0, i);
		 }
		 for(int  i =0;i<a.length;i++){
			 System.out.println(a[i]);
		 }
		
	}

	private static void makeHeap(int[] a,int n) {
		for(int i= n/2-1;i>=0;i--){
			adjust(a,i,n);
		}
	}

	private static void adjust(int[] a, int i, int n) {
		int j = 2*i+1;//left child
		while(j<n){
			if(j+1<n&&a[j]<a[j+1]) j++;
			if(a[i]>=a[j]) break;
			int temp = a[i];
			a[i]=a[j];
			a[j]=temp;
			i=j;
			j=2*i+1;
		}
		
	}

//	private static void swap(int i, int j) {
//		int temp = i;
//		i=j;
//		j=temp;	
//	}
}
