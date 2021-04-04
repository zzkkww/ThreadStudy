package com.JUC20200604.SemphareDemo;/*
 *FileName:  SemaphoreDemo1
 * Author:   Administrator
 * Date  :   2020/6/5 13:49
 * */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo1 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        for (int i = 1; i < 6; i++) {
            new Thread(
                    ()->{
                        try {
                            semaphore.acquire();
                            System.out.println(Thread.currentThread().getName()+"抢到车位");
                            TimeUnit.SECONDS.sleep(2);
                            System.out.println(Thread.currentThread().getName()+"离开车位");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            semaphore.release();
                        }
                    }
            ).start();
        }
    }
}
