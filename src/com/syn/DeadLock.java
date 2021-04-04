package com.syn;

public class DeadLock {
    //死锁：多个线程互相抱着对方需要的资源，然后形成僵持
    public static void main(String[] args) {
        Makeup q1=new Makeup (0,"灰姑凉");
        Makeup q2=new Makeup (1,"白雪公主");
        q1.start ();
        q2.start ();
    }
}

class Lipstick{

}
class Mirror{

}

class Makeup extends Thread {
    static Lipstick lipstick = new Lipstick ();
    static Mirror mirror = new Mirror ();

    int choice;
    String girName;

    Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girName = girlName;
    }

    @Override
    public void run() {
        //化妆
        try {
            makeup ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

    //化妆，互相持有对方的锁，就是需要拿到对方的资源
    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                //获得口红的锁
                System.out.println (this.girName + "获得口红的锁");
                Thread.sleep (1000);

                synchronized (mirror) {
                    System.out.println (this.girName + "获得镜子的锁");
                }
            }
        } else {
            synchronized (mirror) {
                //获得口红的锁
                System.out.println (this.girName + "获得镜子的锁");
                Thread.sleep (2000);

                synchronized (lipstick) {
                    System.out.println (this.girName + "获得口红的锁");
                }
            }
        }
    }
}