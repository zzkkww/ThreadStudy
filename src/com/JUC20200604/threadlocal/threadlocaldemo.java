package com.JUC20200604.threadlocal;
/*
 *FileName:  threadlocaldemo
 * Author:   Administrator
 * Date  :   2020/7/25 14:43
 * */

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 一、ThreadLocal是什么
 *
 * 从名字我们就可以看到ThreadLocal叫做线程变量，意思是ThreadLocal中填充的变量属于当前线程，该变量对其他线程而言是隔离的。ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量。
 *
 * 从字面意思来看非常容易理解，但是从实际使用的角度来看，就没那么容易了，作为一个面试常问的点，使用场景那也是相当的丰富：
 *
 * 1、在进行对象跨层传递的时候，使用ThreadLocal可以避免多次传递，打破层次间的约束。
 *
 * 2、线程间数据隔离
 *
 * 3、进行事务操作，用于存储线程事务信息。
 *
 * 4、数据库连接，Session会话管理。
 *
 * 现在相信你已经对ThreadLocal有一个大致的认识了，下面我们看看如何用？
 */
public class threadlocaldemo {

    public static void main(String[] args) {
        ThreadLocal<Object> local = new ThreadLocal<>();
        //新建一个随机数类
        Random random = new Random();
        //使用java8的stream新建5个线程
        IntStream.range(0,5).forEach(a->new Thread(()->{
            //为每一个线程设置相应的local值
            local.set(a+" "+random.nextInt(10));
            System.out.println("线程和local值分别是 "+local.get());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
    }
/**
 * sout
 * 线程和local值分别是 1 5
 * 线程和local值分别是 2 2
 * 线程和local值分别是 0 4
 * 线程和local值分别是 3 3
 * 线程和local值分别是 4 6
 */
}
