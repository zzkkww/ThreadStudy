package com.JUC20200604;/*
 *FileName:  ticketdemo01
 * Author:   Administrator
 * Date  :   2020/6/4 21:11
 * */

public class ticketdemo01 {

    public static void main(String[] args) {
        ticket ticket = new ticket();
        new Thread(()->{
            for (int i = 0; i <30 ; i++) {
                ticket.sale();
            }

        },"a").start();

        new Thread(
                ()-> {
                    for (int i = 0; i <30; i++) {
                        ticket.sale();
                    }
                },"b").start();

        new Thread(
                ()->{
                    for (int i = 0; i <30 ; i++) {
                        ticket.sale();
                    }
                }
        ,"c").start();
    }



}

class ticket{
    private int number=50;

    public void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"票数"+(number--)+"剩余"+(number));
        }
    }
}