package com.JUC20200604.demo03pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**自我实现生产者消费者问题
 * @author zkw
 * @date 2021-04-04
 **/
public class pc5 {

    public static void main(String[] args) {
        Data5 date = new Data5();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                date.A();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                date.B();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                date.C();
            }
        },"C").start();
    }
}


class Data5{
    private  Lock lock=new ReentrantLock();
    private  Condition condition1=lock.newCondition();
    private  Condition condition2=lock.newCondition();
    private  Condition condition3=lock.newCondition();
    private  int number =1;

    public  void A(){
        lock.lock();
        try {
            while (number!=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"AAAA");
            number=2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public  void B(){
        lock.lock();
        try {
            while (number!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"BBB");
            number=3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public  void C(){
        lock.lock();
        try {
            while (number!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"CCC");
            number=1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

