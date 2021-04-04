package com.zhao;

public class TestStop  implements Runnable{
    private boolean flag=true;

    @Override
    public void run() {
        int i=0;
        while (flag){
            System.out.println ("run...Thread"+i++);
        }
    }

    public void stop(){
        this.flag=false;
    }



    public static void main(String[] args) {
        TestStop testStop = new TestStop();

        new Thread (testStop).start ();

        for (int i = 0; i < 1000; i++) {
            System.out.println ("main"+i);
                if (i==900){
                    //调用Stop方法切换标志位
                    testStop.stop ();
                    System.out.println ("线程该停止了");
                }
        }
    }
}
