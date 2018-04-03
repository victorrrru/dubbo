package com.fww.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By victorrrr on 2017/8/10
 */
public class LambdaT {

    @Test
    public void test1() {
        List<String> list = Arrays.asList("lianggzone", "spring", "summer", "autumn", "winter");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        for (String s: list
             ) {
            System.out.println(s);
        }
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("lianggzone", "spring", "summer", "autumn", "winter");
//        System.out.println(list.stream().reduce((x,y)->x));
        list.stream().collect(Collectors.toList());
    }
}
