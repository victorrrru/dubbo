package com.fww.collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by Administrator on 2017/7/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HashMapTest {

    @Test
    public void test1() {
        Map<Integer, String> map = new HashMap<>();
        String q = map.put(1, "张三");
        String a = map.put(null, "nfkdjfbk");
        map.put(1,"dbghsdf");
        map.put(2, "李四");
        map.put(3, "王五");

        /**
         * map没有iterator,但set有（collection）
         * 迭代器模式
         */
        Set<Integer> set = map.keySet();
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            String value = next.getValue();
            Integer key = next.getKey();
        }
        /**
         * foreach模式
         */
        for (Map.Entry<Integer, String> entry: map.entrySet()) {
            System.out.println(entry.getValue());
        }

        for (Integer key: map.keySet()) {
            System.out.println(map.get(key));
        }
    }

    @Test
    public void test2() {
        Hashtable<Integer, String> map = new Hashtable<>();
        map.put(1, "张三");
        map.put(2, "李四");
        map.put(3, "王五");

        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            String value = next.getValue();
            Integer key = next.getKey();
            System.out.println(key + ":" + value);
        }

        /**
         * foreach模式
         */
        for (Map.Entry<Integer, String> entry: map.entrySet()) {
            System.out.println(entry.getValue());
        }

        /**
         * collection.elements()每次生成的枚举迭代器都不是一个对象
         */
        Enumeration<String> values = map.elements();
        while (map.elements().hasMoreElements()) {
            String s = map.elements().nextElement();
            System.out.println(s);
        }
        for (Enumeration<String> elements = map.elements();  elements.hasMoreElements(); ) {
            System.out.println(elements.nextElement());
        }
    }

    @Test
    public void test3() {
        Student s = new Student();
        s.setId(1);
        Student ss = new Student();
        ss.setId(1);
        System.out.println(s.hashCode());
        System.out.println(ss.hashCode());
        System.out.println(s.equals(ss));
    }

}
