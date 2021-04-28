package leetcode;

public class Leetcode1143 {
    public static int longestCommonSubsequence(String text1, String text2) {

        int[][] ret = new int[text1.length()+1][text2.length()+1];
        if(text1.contains(text2) || text2.length() ==0) return text2.length();
        if(text2.contains(text1) || text1.length() ==0) return text1.length();
        if(text1.charAt(0)==text2.charAt(0)) ret[0][0] = 1;

        for(int i =1;i<text1.length();i++){
            for(int j=1;i<text2.length();j++){
                if(text1.charAt(i) == text2.charAt(j)) ret[i][j] = ret[i-1][j-1]+1;
                else if(ret[i-1][j] > ret[i][j-1]) ret[i][j] = ret[i-1][j];
                else ret[i][j] = ret[i][j-1];
            }
        }

        return ret[text1.length()][text2.length()];

    }

    public static void main(String[] args) {
        int ret = longestCommonSubsequence("a","ca");
        System.out.println(ret);
    }

}
