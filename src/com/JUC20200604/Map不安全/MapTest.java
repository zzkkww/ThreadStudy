package com.JUC20200604.Map不安全;/*
 *FileName:  MapTest
 * Author:   Administrator
 * Date  :   2020/6/5 0:09
 * */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    public static void main(String[] args) {
        //不安全Map<String, String> map = new HashMap<>();
        //方法一Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
        //方法二
        ConcurrentHashMap map = new ConcurrentHashMap();
        for (int i = 1; i <50 ; i++) {
            new Thread(()->{  //这种都是事先runable的
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

}
