package com.fww.muiltThread;

import org.junit.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 范文武
 * @date 2018/05/15 14:02
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class MyTest {
    //junit不支持多线程
    //线程计数器
    private CountDownLatch countDownLatch = new CountDownLatch(2);
    @Test
    public void test1() {
        MyTest myTest = new MyTest();
        ExecutorService exec = Executors.newFixedThreadPool(10);
        exec.execute(() -> {
            myTest.getMessage1();
            //每执行完成一条线程，调用countDown()使计数器减1
            countDownLatch.countDown();
        });
        exec.execute(() -> {
            myTest.getMessage2();
            //每执行完成一条线程，调用countDown()使计数器减1
            countDownLatch.countDown();
        });
        try {
            //主线程调用await方法使其等待，当计数器为0时才继续执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void getMessage1() {
        System.out.println("方法一");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after  -----   方法一");
    }
    private synchronized void getMessage2() {
        System.out.println("方法二");
        getMessage1();
    }
}
