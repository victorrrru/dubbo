package com.fww.muiltThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 范文武
 * @date 2018/05/29 09:42
 * aba解决办法  AtomicStampedReference
 */
public class ABATest {
    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedRef.compareAndSet(100, 101,
                    atomicStampedRef.getStamp(), atomicStampedRef.getStamp()+1);
            atomicStampedRef.compareAndSet(101, 100,
                    atomicStampedRef.getStamp(), atomicStampedRef.getStamp()+1);
        }).start();

        new Thread(() -> {
            int stamp = atomicStampedRef.getStamp();
            System.out.println("before sleep : stamp = " + stamp);    // stamp = 0
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after sleep : stamp = " + atomicStampedRef.getStamp());//stamp = 1
            boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp+1);
            System.out.println(c3);        //false
        }).start();
    }
}
