package com.JUC20200604.八锁;/*
 *FileName:  Test1
 * Author:   Administrator
 * Date  :   2020/6/4 23:00
 * */

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Test1 {
    public static void main(String[] args) {
        Phone phone=new Phone();

        new Thread(()->{
            phone.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();
        },"B").start();


//        HashMap<Integer, Object> map = new HashMap<>();
//        map.put(1,new Thread(()->{
//            System.out.println(Thread.currentThread().getName());
//        }));
//        map.put(2,"1");
//        for (Integer o : map.keySet()) {
//            System.out.println(map.get(o));
//        }
    }

}

class Phone{
    public synchronized  void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }
}