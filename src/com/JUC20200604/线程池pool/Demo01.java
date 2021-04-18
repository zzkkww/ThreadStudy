package com.JUC20200604.线程池pool;/*
 *FileName:  Demo01
 * Author:   Administrator
 * Date  :   2020/6/5 15:58
 * */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo01 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//只能有一条线程进行操作，单个线程池
        //ExecutorService threadPool=Executors.newFixedThreadPool(5);//创建一个固定的线程池的大小
        //ExecutorService threadPool=Executors.newCachedThreadPool();//可伸缩 大小

        //线程池用完 ，一定要关闭线程池
        try {
            for (int i = 0; i <10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"pk");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完 ，一定要关闭线程池
            threadPool.shutdown();
        }

    }
}
