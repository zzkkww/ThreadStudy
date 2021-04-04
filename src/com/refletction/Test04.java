package com.refletction;


import java.lang.annotation.ElementType;

//所有类型的Class
public class Test04 {
    private static Object ClassLoader;

    public static void main(String[] args) throws ClassNotFoundException {
        Class c1=Object.class;
        Class c2=Comparable.class;
        Class c3=String[].class;
        Class c4=int [][].class;
        Class c5=Override.class;//注解
        Class c6= ElementType.class;//枚举类型
        Class c7=Integer.class;//基本数据类型
        Class c8=void.class;
        Class c9=Class.class;

        System.out.println (c1);
        System.out.println (c2);
        System.out.println (c3);
        System.out.println (c4);
        System.out.println (c5);
        System.out.println (c6);
        System.out.println (c7);
        System.out.println (c8);
        System.out.println (c9);

        ClassLoader=Class.forName ("java.lang.Object").getClassLoader ();
        System.out.println (ClassLoader);
    }
}
