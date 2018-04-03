package com.fww.collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created by Administrator on 2017/7/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListTest {

    @Test
    public void linkedListTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(-8);
        list.add(30);
//
//        Collections.sort(list);
//        Collections.reverse(list);
        Comparator<Object> comparator = Collections.reverseOrder();
        Collections.sort(list, comparator);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(-8);
        list.add(30);

        List<Integer> list1 = new ArrayList<>();
        list1.add(3);

        list.toArray(new Integer[0]);
        Comparator<Object> comparator = Collections.reverseOrder();
        Collections.sort(list, comparator);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void test3() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(-8);
        list.add(30);

        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            int i = iterator.nextIndex();
            System.out.println(i);
        }
    }

}

