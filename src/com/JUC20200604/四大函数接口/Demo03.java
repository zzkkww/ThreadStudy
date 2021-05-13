package com.JUC20200604.四大函数接口;/*
 *FileName:  Demo03
 * Author:   Administrator
 * Date  :   2020/6/5 18:33
 * */

import java.util.function.Consumer;

public class Demo03 {
    public static void main(String[] args) {
       /* Consumer<String> consumer= new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };*/
        Consumer<String> consumer=(str)->{
            System.out.println(str);
            System.out.println("test");
        };
        consumer.accept("哈哈哈哈哈");
    }
}
