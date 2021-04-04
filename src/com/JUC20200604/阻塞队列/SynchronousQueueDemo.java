package com.JUC20200604.阻塞队列;/*
 *FileName:  SynchronousQueueDemo
 * Author:   Administrator
 * Date  :   2020/6/5 15:43
 * */

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/*同步队列和其他的BlockQueue不一样，SynchronousQueue 不存储元素
* put了一个元素，必须从里面先take取出来，否则不能再put进去值*/
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{

            try {
                System.out.println(Thread.currentThread().getName()+"put1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName()+"put2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName()+"put3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();


        new Thread(()->{

            try {
                System.out.println(Thread.currentThread().getName()+"=="+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);

                System.out.println(Thread.currentThread().getName()+"=="+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);

                System.out.println(Thread.currentThread().getName()+"=="+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
