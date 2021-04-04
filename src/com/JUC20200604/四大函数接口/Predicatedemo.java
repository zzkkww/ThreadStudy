package com.JUC20200604.四大函数接口;/*
 *FileName:  Predicatedemo
 * Author:   Administrator
 * Date  :   2020/6/5 18:27
 * */

import java.util.function.Predicate;

public class Predicatedemo {
    public static void main(String[] args) {

        //判断字符串是否为空可以使用
      /* Predicate<String> predicate= new Predicate<String>() {
            @Override
            public boolean test(String str) {

                return str.isEmpty();

            }
        };*/
        Predicate<String> predicate=(str)->{
            return str.isEmpty();
        };
        System.out.println(predicate.test("sdfdsf"));
    }
}
