package com.syn.ziwoshixian;

/**
 * @author zkw
 * @date 2021-04-02
 **/
public class DeadLock {
    public static void main(String[] args) {
        MakeUp q1 = new MakeUp(0, "白雪");
        MakeUp q2 = new MakeUp(1, "老巫婆");

        q1.start();
        q2.start();
    }
}

/**
 * 口红
 */
class L {

}

/**
 * 镜子
 */
class M {

}

class MakeUp extends Thread {
    //这里如果不加static就不会早于创建对象的时候创建，那么这个锁就没有意义了
   static  L l = new L();
   static M m = new M();

    int choice;
    String name;

    MakeUp(int choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    private void makeUp() throws InterruptedException {
        if (choice == 0) {
            synchronized (l) {
                System.out.println(this.getName() + "获得口红的锁");
//                Thread.sleep(1000);

                synchronized (m) {
                    System.out.println("获得镜子的锁");
                }
            }
        } else {
            synchronized (m) {
                System.out.println(this.getName() + "获得镜子的锁");
//                Thread.sleep(2000);
                synchronized (l) {
                    System.out.println("获得口红的锁");
                }
            }
        }
    }


    @Override
    public void run() {
        try {
            makeUp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}