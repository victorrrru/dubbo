package com.fww.decorator.test1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by victorrrr on 2017/6/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class TestDecorator {

    @Test
    public void test1() {
        Hamburger hamburger = new ChickenHambur();
        System.out.println(hamburger.getName() + ":" + hamburger.getPrice());
        Lettuce lettuce = new Lettuce(hamburger);
        System.out.println(lettuce.getName() + ":" + lettuce.getPrice());
        Pepper pepper = new Pepper(lettuce);
        System.out.println(pepper.getName() + ":" + pepper.getPrice());
        Ketchup ketchup = new Ketchup(pepper);
        System.out.println(ketchup.getName() + ":" + ketchup.getPrice());
        Pepper pepper1 = new Pepper(new Ketchup(new Lettuce(hamburger)));
        System.out.println(pepper1.getName() + ":" + pepper1.getPrice());
    }

}
