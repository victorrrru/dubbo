package com.fww.collection.ActiveMQ;

import javax.jms.JMSException;

/**
 * @author Administrator
 * @date 2017/12/26 17:34
 */
public class TestConsumer implements Runnable{
    static Thread thread = null;
    public static void main(String[] args) throws InterruptedException {
        thread = new Thread(new TestConsumer());
        thread.start();
        while (true){
            //时刻监听消息队列，如果线程死了，则新建一个线程
            boolean alive = thread.isAlive();
            System.out.println("当前线程状态："+alive);
            if(!alive){
                thread = new Thread(new TestConsumer());
                thread.start();
                System.out.println("线程重启完成");
            }
            Thread.sleep(1000);
        }
    }
    @Override
    public void run() {
        try {
            Consumer consumer = new Consumer();
            consumer.consumerMessage();
            while (Consumer.isconnection) {
                //System.out.println(123);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
