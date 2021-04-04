package com.JUC20200604.LOCK.可重入锁;/*
 *FileName:  Demo01
 * Author:   Administrator
 * Date  :   2020/6/5 23:25
 * */
//synchronized
public class Demo01 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(
                ()->{
                    phone.sms();
                }
        ,"A").start();

        new Thread(
                ()->{
                    phone.sms();
                }
                ,"B").start();
    }
}

class Phone{

    public synchronized void sms(){
        System.out.println(Thread.currentThread().getName()+"sms");
        call();//这里也有锁
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+"call");

    }
}