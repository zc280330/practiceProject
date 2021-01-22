package leetcode;


import sort.main;

import java.util.ArrayList;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class leetcode6 {
    public static String convert(String s, int numRows) {
        int len = s.length();
        if(numRows<=1) return s;
        List<StringBuilder> container = new ArrayList<>();
        for(int i=0;i<numRows;i++){
            container.add(new StringBuilder());
        }
        int count = 1;
        int direction = -1;
        for (int i = 0; i < len; i++) {
            container.get(count - 1).append(String.valueOf(s.charAt(i)));
            if (count == numRows) {
                direction *= -1;
            } else if (count == 1) {
                direction *= -1;
            }
            count = count + 1 * direction;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            sb.append(container.get(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING",
                3));
    }
}
