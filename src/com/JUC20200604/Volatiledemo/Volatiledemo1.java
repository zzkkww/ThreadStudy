package com.JUC20200604.Volatiledemo;/*
 *FileName:  Volatiledemo1
 * Author:   Administrator
 * Date  :   2020/6/5 20:43
 * */

import java.util.concurrent.TimeUnit;

public class Volatiledemo1 {
    private volatile   static int num=0;

    public static void main(String[] args) {

        new Thread(()->{
            while (num==0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num=1;
        System.out.println(num);

    }
}
