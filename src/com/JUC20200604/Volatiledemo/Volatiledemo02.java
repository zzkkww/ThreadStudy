package com.JUC20200604.Volatiledemo;/*
 *FileName:  Volatiledemo02
 * Author:   Administrator
 * Date  :   2020/6/5 20:50
 * */

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Volatiledemo02 {
    //变成原子类的integer
    private  static AtomicInteger num=new AtomicInteger();


    public  static void add(){


        //num++; 此时不能直接++了
        num.getAndIncrement(); //加一 使用cas 底层原理


    }
    
    public static void main(String[] args) {
        for (int i = 1; i <=20 ; i++) {
            new Thread(
                    ()->{
                        for (int j = 0; j < 1000; j++) {
                            add();//理论上为2W
                        }
                    }
            ).start();
        }

        while (Thread.activeCount()>2){
            //main, gc线程默认存在的
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+""+num);
    }
}
