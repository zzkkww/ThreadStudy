package com.JUC20200604.List不安全;/*
 *FileName:  ListTest
 * Author:   Administrator
 * Date  :   2020/6/4 23:19
 * */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

//java.util.ConcurrentModificationException 并发修改异常
public class ListTest {
    public static void main(String[] args) {
        //第一种用vector可以解决
        //第二种解决方案List<String> list = Collections.synchronizedList(new ArrayList<>());
        //不安全ArrayList<String> list = new ArrayList<String>();
        //第三种CopyOnWrite cow计算机程序设计领域的已有优化策略

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i < 100; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,20));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
