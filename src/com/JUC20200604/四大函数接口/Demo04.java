package com.JUC20200604.四大函数接口;/*
 *FileName:  Demo04
 * Author:   Administrator
 * Date  :   2020/6/5 18:38
 * */

import java.util.function.Supplier;

public class Demo04 {
    public static void main(String[] args) {
       /* Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1024;
            }
        };*/

        Supplier<Integer> supplier=()->{
            return 1024*1024;
        };
        System.out.println(supplier.get());


    }
}
