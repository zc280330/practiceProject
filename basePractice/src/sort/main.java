package sort;

import java.util.*;

public class main {

	public static Set<String> cur = new HashSet<String>();
    public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		queue.poll();
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(",");       
        int m = Integer.parseInt(line[0]);
        int n = Integer.parseInt(line[1]);
        int[][]rec=new int[m][n];
        for(int i = 0;i<m;i++) {
        	line = in.nextLine().split(",");
        	for(int j=0;j<n;j++){
        		rec[i][j]=Integer.parseInt(line[j]);
        	}
        }
        List<Set<String>> result = new ArrayList<>();
        for(int i = 0;i<m;i++) {
        	for(int j=0;j<n;j++){
        		int a = rec[i][j];
        		if(a==1) {	
        			cur.clear();
        			search(i,j,cur,rec);
        			result.add(new HashSet<String>(cur));
        		}
        		
        	}
        }
        int max = 0;
        for(Set ls :result){
        	max=Math.max(ls.size(), max);
        }
        System.out.println(result);
        System.out.println(result.size());
        System.out.println(max);
        
    }
    public static void search(Integer i,Integer j,Set cur,int[][] rec){
    	if(i<0||j<0||i>=rec.length||j>=rec[0].length) return;
    	if(rec[i][j]==1) {
    		String add = i.toString()+"*"+j.toString();
			cur.add(add);
			rec[i][j]=0;
			search(i-1,j-1,cur,rec);
			search(i-1, j, cur, rec);
			search(i+1, j, cur, rec);
			search(i, j-1, cur, rec);
			search(i, j+1, cur, rec);
			search(i+1, j-1, cur, rec);
			search(i-1, j+1, cur, rec);
			search(i+1, j+1, cur, rec);
			System.out.println(cur);
		}
    	
	}
}