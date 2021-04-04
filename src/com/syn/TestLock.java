package com.syn;

import com.zhao.TestThread2;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        TestLock2 testThread2=new TestLock2 ();
        new Thread (testThread2).start ();
        new Thread (testThread2).start ();
        new Thread (testThread2).start ();
    }
}

class TestLock2 implements Runnable{
    int ticketNums=10;
    //定义Lock锁
    private final ReentrantLock lock=new ReentrantLock ();

    @Override
    public void run() {
        while (true){
            try{
                lock.lock ();//加锁
                if (ticketNums>=0){
                    try {
                        Thread.sleep (1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                    System.out.println (ticketNums--);
                    System.out.println (Thread.currentThread ().getName ()+"名");
                }else {
                    break;
                }
            }finally {
                //解锁
                lock.unlock ();
            }

        }
    }
}