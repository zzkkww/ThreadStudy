package com.JUC20200604.线程池pool;/*
 *FileName:  ThreadPoolExecutorDemo
 * Author:   Administrator
 * Date  :   2020/6/5 16:20
 * */

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),//候客区
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()//拒绝策略 银行满了 还有人进来，不处理这个人抛出异常

        );

        try {
            //最大承载 deque+max
            /*java.util.concurrent.RejectedExecutionException: Task com.JUC20200604.线程池pool.ThreadPoolExecutorDemo$$Lambda$1/1747585824@58372a00 rejected from java.util.concurrent.ThreadPoolExecutor@4dd8dc3[Running,
             pool size = 5, active threads = 5,
             queued tasks = 3, completed tasks = 0]*/
            for (int i = 1; i <=9 ;i++) {
                //使用了线程池后 使用线程池来创建线程 不用new Thread().start
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
}
