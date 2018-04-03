package com.fww.Thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author Administrator
 * @date 2017/12/25 16:37
 */
public class MyThreadImplementCallable implements Callable<String> {

        String name;
        public MyThreadImplementCallable(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName()+"开始工作==============");
            Random random  = new Random();
//            Thread.sleep(random.nextInt(5)*100);  //模拟执行业务
            return name+":执行完成";
        }

        public static void main(String[] args) throws Exception{
            MyThreadImplementCallable callable = new MyThreadImplementCallable("测试");
            FutureTask<String> futureTask = new FutureTask<>(callable);
            Thread thread = new Thread(futureTask);

            thread.start();
            String result = futureTask.get();  //获取任务线程执行结果
            System.out.println("线程的执行结果："+result);
            System.out.println("线程的执行结果："+ Runtime.getRuntime().availableProcessors());
        }
}
