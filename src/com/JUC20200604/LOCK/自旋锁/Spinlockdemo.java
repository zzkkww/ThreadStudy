package com.JUC20200604.LOCK.自旋锁;/*
 *FileName:  Spinlockdemo
 * Author:   Administrator
 * Date  :   2020/6/5 23:34
 * */

import java.util.concurrent.atomic.AtomicReference;

public class Spinlockdemo {



   AtomicReference<Thread> atomicReference= new AtomicReference<>();


   //加锁
    public void myLock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"==> mylock");

        while (!atomicReference.compareAndSet(null,thread)){

        }

    }

    //解锁
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==>解锁 myUnlock");
        atomicReference.compareAndSet(thread,null);
    }
}
