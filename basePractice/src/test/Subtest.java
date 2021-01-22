package test;

import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: zengchao
 * @Date: 2019-06-12 17:02
 */
public class Subtest {

    public static void main(String[] args) {
        DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String lastBinding = "2020-02-25 08:23:21";
        final LocalDateTime lastBindDT = LocalDateTime.parse(lastBinding, FORMATER);
        long lastBindDays = ChronoUnit.DAYS.between(lastBindDT.toLocalDate(), LocalDate.now());
        System.out.println(lastBindDays);
    }



}
