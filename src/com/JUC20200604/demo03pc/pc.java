package com.JUC20200604.demo03pc;/*
 *FileName:  pc
 * Author:   Administrator
 * Date  :   2020/6/4 22:21
 * */

/**
 * 生产者消费者问题  wait notifyAll
 */
public class pc {
    public static void main(String[] args) {
        Data data = new Data();
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
    }
}

class Data{
    private int number=0;
    public synchronized  void increment() throws InterruptedException {
//        如果使用if  来判断number！=0 会出现虚假唤醒问题
        while (number!=0){
            this.wait();
        }
        number ++;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>"+number);
        notifyAll();
    }
}