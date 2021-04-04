package com.JUC20200604.CAS;/*
 *FileName:  CASDemo
 * Author:   Administrator
 * Date  :   2020/6/5 22:28
 * */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo {

    //Interger  2000太大了 -128---127  改成1就不会错了
    //AtomicStampedReference注意 如果泛型是一个包装类 注意对象的引用问题
    static  AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

    //compareAndSet() 比较并交换

    public static void main(String[] args) {
        //AtomicInteger atomicInteger = new AtomicInteger(2020);

        new Thread(
                ()->{
                    int stamp = atomicStampedReference.getStamp(); //获得版本号
                    System.out.println("a1=>"+stamp);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Verson +1 版本号
                    System.out.println(atomicStampedReference.compareAndSet(1, 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

                    System.out.println("a2=>"+atomicStampedReference.getStamp());

                    //版本号应该为3
                    System.out.println(atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

                    System.out.println("a3=>"+atomicStampedReference.getStamp());
                }
        ,"A").start();

        new Thread(
                ()->{
                    int stamp = atomicStampedReference.getStamp(); //获得版本号
                    System.out.println("b1=>"+atomicStampedReference.getStamp());

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(atomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1));

                    System.out.println("b2=>"+atomicStampedReference.getStamp());
                }
                ,"B").start();

    }
}
