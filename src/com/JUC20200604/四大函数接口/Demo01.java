package com.JUC20200604.四大函数接口;/*
 *FileName:  Demo01
 * Author:   Administrator
 * Date  :   2020/6/5 18:22
 * */

import java.util.function.Function;

public class Demo01 {
    public static void main(String[] args) {
            //工具类，输出输入的值
        /*Function function = new Function<String,String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };*/

        Function function=(str)->{
            return str;
        };

        System.out.println(function.apply("123"));
    }
}
