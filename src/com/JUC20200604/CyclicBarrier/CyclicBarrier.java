package com.JUC20200604.CyclicBarrier;/*
 *FileName:  CyclicBarrier
 * Author:   Administrator
 * Date  :   2020/6/5 13:41
 * */

import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrier {
    public static void main(String[] args) {

        java.util.concurrent.CyclicBarrier cyclicBarrier = new java.util.concurrent.CyclicBarrier(
                7,()->{
            System.out.println("召唤神龙成功");
        }
        );


        for (int i = 1; i <=7 ; i++) {
            final  int temp=i; //这里需要定义一个中介变量才能拿到i
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集了"+temp+"龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
