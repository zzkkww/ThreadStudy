package com.syn.ziwoshixian;

/**死锁产生案例
 * @author zkw
 * @date 2021-04-02
 **/
public class DeadLockDemo2 {
    public static void main(String[] args) {
        Play q1 = new Play(0, "张三");
        Play q2 = new Play(1, "李四");
        q1.start();
        q2.start();
    }
}


class Telephone{

}
class Computer{

}

class Play extends Thread{
    static Telephone telephone=new Telephone();
    static Computer computer=new Computer();

    String name;
    int choice;

    Play(int choice,String name){
        this.name=name;
        this.choice=choice;
    }

    @Override
    public void run(){
        try {
            playEqu();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playEqu(){
        if (choice==0){
            synchronized (telephone){
                System.out.println(this.getName()+"获得手机锁");

                synchronized (computer){
                    System.out.println("获得电脑的锁");
                }
            }
        }else {
            synchronized (computer){
                System.out.println(this.getName()+"获得电脑的锁");

                synchronized (telephone){
                    System.out.println("尝试获取手机的锁");
                }
            }
        }
    }

}