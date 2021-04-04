package com.JUC20200604.阻塞队列;/*
 *FileName:  zuse
 * Author:   Administrator
 * Date  :   2020/6/5 14:50
 * */

import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class zuse {
    public static void main(String[] args) {
        test2();
    }
    /*抛出异常
    * */
    public static void test1(){
        //ArrayBlockingQueue<> 这里是建议写入大小避免扩容 不是写泛型
        ArrayBlockingQueue blockingQueue= new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println("==============");
        //会抛出异常Exception in thread "main" java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.add("d"));




    }

    public static void test2(){
        //ArrayBlockingQueue<> 这里是建议写入大小避免扩容 不是写泛型
        ArrayBlockingQueue blockingQueue= new ArrayBlockingQueue<>(3);


        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));



        //这里超过了设置的大小3页不会抛出异常
        System.out.println(blockingQueue.offer("d"));
        //检测对首元素
        System.out.println(blockingQueue.element());
        System.out.println("==========");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

    }
}
