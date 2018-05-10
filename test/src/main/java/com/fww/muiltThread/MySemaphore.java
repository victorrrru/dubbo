package com.fww.muiltThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author 范文武
 * @date 2018/05/10 10:05
 * Semaphore:控制资源的访问，控制资源访问的线程数量，比如停车场只能停5辆车。
 * 停满后就不能再有车进入停车场，当有一辆车开走，才能有车停进来
 */
public class MySemaphore {
    public static void main(String[] args) {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 50; index++) {
            final int NO = index;
            Runnable run = () -> {
                try {
                    // 获取许可
                    semp.acquire();
                    System.out.println("Accessing: " + NO);
                    Thread.sleep((long) (Math.random() * 6000));
                    // 访问完后，释放
                    semp.release();
                    //availablePermits()指的是当前信号灯库中有多少个可以被使用
                    System.out.println("-----------------" + semp.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }
}
