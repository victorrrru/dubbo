package com.fww.collection.Queue;

/**
 * @author Administrator
 * @date 2017/12/26 10:38
 */
public class Business {
    private boolean bool = true;
    public synchronized void main(int loop) throws InterruptedException {
        while(bool) {
            this.wait();
        }
        for(int i = 0; i < 100; i++) {
            System.out.println("main thread seq of " + i + ", loop of " + loop);
        }
        bool = true;
        this.notify();
    }
    public synchronized void sub(int loop) throws InterruptedException {
        while(!bool) {
            this.wait();
        }
        for(int i = 0; i < 10; i++) {
            System.out.println("sub thread seq of " + i + ", loop of " + loop);
        }
        bool = false;
        this.notify();
    }
}
