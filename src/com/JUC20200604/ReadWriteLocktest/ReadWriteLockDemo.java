package com.JUC20200604.ReadWriteLocktest;/*
 *FileName:  ReadWriteLockDemo
 * Author:   Administrator
 * Date  :   2020/6/5 14:27
 * */

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        //写入
        for (int i = 1; i < 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();

        }

        //读取
        for (int i = 1; i < 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();

        }
    }





}

class MyCache{

    private volatile Map<String,Object> map=new HashMap<>();

    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();


    //存 写
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"写入"+key);


        try {
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入ok");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
            readWriteLock.writeLock().unlock();
        }

    //存 写
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取OK");
    }
}