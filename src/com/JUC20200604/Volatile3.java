package com.JUC20200604;/*
 *FileName:  Volatile3
 * Author:   Administrator
 * Date  :   2020/7/6 12:04
 * */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Volatile3 {
    private volatile static int num=0;
    static Lock lock = new ReentrantLock();

    public static void add(){

        lock.lock();
        num++;
        lock.unlock();
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
