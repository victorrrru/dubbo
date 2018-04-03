package com.fww.collection.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Administrator
 * @date 2017/12/26 10:03
 */
public class TestBlockingQueue {
    public static void main(String[] args) {
        //新建一个阻塞队列，队列长度是5
        BlockingQueue<String> queue = new LinkedBlockingDeque<>(5);
        //BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        Consumer consumer = new Consumer(queue);
        Product product = new Product(queue);
        for(int i = 0;i<3;i++){
            new Thread(product,"product"+i).start();
        }

        for (int i = 0;i<5;i++){
            new Thread(consumer,"consumer").start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 3; i < 5; i++) {
            new Thread(product, "product"+i).start();
        }
    }
}
