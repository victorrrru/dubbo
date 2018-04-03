package com.fww;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MainTest {

    @Test
    public void test1() {
        Integer a = null;
        Integer b = 1 - a;
        if (b < 0) {
            System.out.print("通过");
        } else {
            System.out.print("Bu通过");
        }
    }

    @Test
    public void test2() {
        Integer a = 5;
        a <<= 1;
        long b = 1 << 32;
        System.out.println(a);
    }

    @Test
    public void test3() {
        /**
         * 8种基本类型是不能被泛型的，
         */
        Byte[] bytes = {1, 5, 6};
        List list = Arrays.asList( bytes);
        System.out.println(list.size());
        System.out.println(list.get(0));
    }

    @Test
    public void test4() {
        System.out.println(Instant.now());
        System.out.println(LocalDateTime.now());
        LocalTime time = LocalTime.now().withNano(0).withSecond(0).withMinute(0);
        System.out.println(time);
        System.out.println(Instant.ofEpochMilli(System.currentTimeMillis()));
    }

    @Test
    public void test5() {
        List<Integer> list1 = new ArrayList();
        list1.add(1);
        list1.add(3);
        list1.add(5);
        list1.add(7);
        list1.add(9);
        List<Integer> list2 = new ArrayList();
        list2.add(4);
        list2.add(6);
        list2.add(8);
        //list2.removeAll(list1);
        //list1.retainAll(list2);
        HashSet<Integer> set = new HashSet<>(list1);
        ArrayList<Integer> list3 = new ArrayList<>();
        for (Integer list : list2) {
            if (set.add(list) == false) {
                list3.add(list);
            }
        }
        list1.removeAll(list3);
        list2.removeAll(list3);
        System.out.println(list3);
        System.out.println(list1);
        System.out.println(list2);
    }
}
