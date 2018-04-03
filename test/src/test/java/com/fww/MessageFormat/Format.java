package com.fww.MessageFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;

/**
 * @author Administrator
 * @date 2018/01/03 11:13
 */
@RunWith(SpringRunner.class)
public class Format {
    @Test
    public void test1() {
        System.out.println(MessageFormat.format("{0}+{1}=?", 1, 2));
    }
}
