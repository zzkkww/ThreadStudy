package com.JUC20200604.threadlocal;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author zkw
 * @date 2021-04-03
 **/
public class ThredLocalDemo2 {

    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
            //开启多个线程来执行任务
        Task task = new Task();
        new Thread(task).start();
        Thread.sleep(1000);
        new Thread(task).start();
    }

    static class Task implements Runnable{


        @Override
        public void run() {
            //两个线程拿到的值都不同，因为那个变量值存在于其线程中，这个变量不共享
            Long result = threadLocal.get();
            if (result==null){
                threadLocal.set(System.currentTimeMillis());
            }
            System.out.println(Thread.currentThread().getName()+"->"+threadLocal.get());
        }
    }
}


