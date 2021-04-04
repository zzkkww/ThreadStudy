package com.JUC20200604.CountDownLatch;/*
 *FileName:  CountDownLatchDemo
 * Author:   Administrator
 * Date  :   2020/6/5 13:34
 * */

import java.util.concurrent.CountDownLatch;

//计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是6
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 1; i <6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"走了");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();//等待计数器归零，再向下执行
        System.out.println("Close door");
        //countDownLatch.countDown();//-1
    }

}
