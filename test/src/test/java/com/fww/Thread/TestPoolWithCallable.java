package com.fww.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Administrator
 * @date 2017/12/25 17:14
 */
public class TestPoolWithCallable {
    public static void main(String[] args) throws Exception{
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for(int i=0;i<1000;i++){
            Future<String> future = pool.submit(new MyThreadImplementCallable(i+1+""));
            //从Future中get结果，这个方法是会被阻塞的，一直要等到线程任务返回结果
            System.out.println("执行结果："+future.get());
        }
        pool.shutdown();
    }
}
