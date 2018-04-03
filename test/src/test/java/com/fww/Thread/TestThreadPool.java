package com.fww.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Administrator
 * @date 2017/12/25 17:19
 */
public class TestThreadPool {
    public static void main(String[] args) throws Exception{
        Future<?> submit;
        //创建缓存线程池
        ExecutorService cachePool = Executors.newCachedThreadPool();

        //用来存在Callable执行结果
        List<Future<?>> futureList = new ArrayList<>();
        for(int i = 0;i<1000;i++){
            //cachePool提交线程，Callable，Runnable无返回值
            submit = cachePool.submit(new MyThreadImplementCallable(""+i+1));
//            submit = cachePool.submit(new Thread(i + ""));

            //把这些执行结果放到list中，后面再取可以避免阻塞
            futureList.add(submit);
        }
        cachePool.shutdown();
        //打印执行结果
        for(Future f : futureList){
            boolean done = f.isDone();
            System.out.println(done?"已完成":"未完成");
            System.out.println("线程返回结果："+f.get());
        }
    }
}
