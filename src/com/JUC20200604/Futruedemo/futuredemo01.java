package com.JUC20200604.Futruedemo;/*
 *FileName:  futuredemo01
 * Author:   Administrator
 * Date  :   2020/6/5 20:18
 * */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class futuredemo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Future找它的实现类

        //发起一个请求
        CompletableFuture<Void> completableFuture =CompletableFuture.runAsync(
                ()->{
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                    }
                    System.out.println(Thread.currentThread().getName()+"结果");
                }
        );

        System.out.println("1111111111");
        completableFuture.get();

    }
}
