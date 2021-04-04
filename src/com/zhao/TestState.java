package com.zhao;


//观测测试线程的状态
public class TestState {
    public static void main(String[] args) {
        Thread thread=new Thread (()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep (1000);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
            System.out.println ("///////");
        });


        //观测状态
        Thread.State state = thread.getState ();
        System.out.println (state);//NEW

        //观测启动后
        thread.start ();
        state=thread.getState ();
        System.out.println (state);//Run

        while (state!=Thread.State.TERMINATED){
            //只要线程不终止，就一直输出状态
            try {
                Thread.sleep (100);
                state = thread.getState ();//更新线程状态
                System.out.println (state);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
    }
}
