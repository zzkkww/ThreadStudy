package com.JUC20200604.Callable;/*
 *FileName:  CallableTest
 * Author:   Administrator
 * Date  :   2020/6/5 0:27
 * */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new Thread().start();//这里无法直接启动继承Callable的
        //这个
        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread);

        new Thread(futureTask,"A").start();
        Object o = futureTask.get();
        System.out.println(o);
    }
}


class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("callable()");
        return 1024;
    }
}