package com.syn.ziwoshixian;

import java.util.HashMap;
import java.util.Map;

/**写的有问题 没有验证到hashmap并发环境下 不安全
 * @author zkw
 * @date 2021-04-02
 **/
public class DeadLockHashMap {

    public static void main(String[] args) {
        A a = new A();


        for (int i = 0; i < 100; i++) {
            new Thread(a).start();
        }




    }
}


class A implements Runnable {

    private static  Map<String, Object> map = new HashMap<>();

    @Override
    public void run() {
        int i=1;
        map.put("A",i--);
        System.out.println("操作完成");
    }
}

