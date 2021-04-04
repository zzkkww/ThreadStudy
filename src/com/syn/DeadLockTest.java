package com.syn;/*
 *FileName:  DeadLockTest
 * Author:   Administrator
 * Date  :   2020/8/1 2:47
 * */

public class DeadLockTest {
    public static void main(String[] args) {
        Makeup2 q1 = new Makeup2(0, "zkw");
        Makeup2 q2 = new Makeup2(1, "白雪公主");
        q1.start();
        q2.start();
    }
}

class Lipstick2{

}

class Mirror2{

}

class Makeup2 extends Thread{
    static Lipstick2 lipstick2 =new Lipstick2();
    static Mirror2 mirror2=new Mirror2();

    int choice;
    String girName;

    Makeup2(int choice,String girName){
        this.choice=choice;
        this.girName=girName;
    }

    @Override
    public void run() {
        //化妆
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //化妆，互相持有对方的锁，就是需要拿到对方的资源
    private void makeup() throws InterruptedException {
        if (choice==0){
            synchronized (lipstick2){
                //获得口红的锁
                System.out.println(this.girName+"获得口红的锁");
                Thread.sleep(1000);
                synchronized (mirror2){
                    System.out.println(this.girName+"获得镜子的锁");
                }
            }
        }else {
            synchronized (mirror2){
                //获得口红的锁
                System.out.println(this.girName + "获得镜子的锁");
                Thread.sleep(2000);
                synchronized (lipstick2){
                    System.out.println(this.girName+"获得口红的锁");
                }
            }
        }
    }
}