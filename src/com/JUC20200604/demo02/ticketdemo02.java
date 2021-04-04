package com.JUC20200604.demo02;/*
 *FileName:  ticketdemo01
 * Author:   Administrator
 * Date  :   2020/6/4 21:11
 * */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ticketdemo02 {

    public static void main(String[] args) {
        ticket ticket = new ticket();
        new Thread(()->{
            for (int i = 0; i <30 ; i++) {
                ticket.sale();
            }

        },"a").start();

        new Thread(
                ()-> {
                    for (int i = 0; i <30; i++) {
                        ticket.sale();
                    }
                },"b").start();

        new Thread(
                ()->{
                    for (int i = 0; i <30 ; i++) {
                        ticket.sale();
                    }
                }
        ,"c").start();
    }



}

class ticket{
    private int number=30;
    Lock lock=new ReentrantLock();

    public  void sale(){

        lock.lock();
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"票数"+(number--)+"剩余"+(number));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}