package com.JUC20200604.List不安全;/*
 *FileName:  SetTest
 * Author:   Administrator
 * Date  :   2020/6/4 23:58
 * */

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {
    public static void main(String[] args) {
        //法1Set<String> set = Collections.synchronizedSet(new HashSet<>());
        //不安全HashSet<String> set = new HashSet<>();
        //方法2

        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <30 ; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,20));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
