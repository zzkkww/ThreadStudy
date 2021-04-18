package com.JUC20200604.demo03pc;/*
 *FileName:  pc
 * Author:   Administrator
 * Date  :   2020/6/4 22:21
 * */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 生产者消费者问题
 * 用Lock替换了Synchronized 然后用里面的Condition 里面的
 * await signalAll替换了 wait  notifyall
 */
public class pc2 {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"a").start();


        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {

                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"c").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"d").start();

    }


}

class Data2{

    private int number=0;

    Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();
    // condition.await();
    //        condition.signalAll();


    public  void increment() throws InterruptedException {
        lock.lock();

        try {
            while (number!=0){
                condition.await();
            }

            number ++;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number==0){
                condition.await();

            }

            number--;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}