package test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Math.pow;
import static java.util.stream.StreamSupport.stream;

/**
 * @Author: zengchao
 * @Date: 2019-06-12 17:01
 */
public class Test {

    private static Long transFromDouble2Long(String toString) {
        if(toString.contains(".") || toString.contains("E")) return Long.parseLong(toString.substring(0,toString.indexOf("E")).replace(".",""));
        else return Long.parseLong(toString);
    }

    public static void main(String[] args) {
        String v = "1.598691049E9";
        Double doubleV = Double.parseDouble(v);
        System.out.println(transFromDouble2Long(doubleV.toString()));

    }
}
