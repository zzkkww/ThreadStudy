package com.zhao;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习THread
public class TestThread2  extends  Thread{

    private  String url;//网络图片地址
    private String name;//保存的文件名

    public TestThread2(String url,String name){
        this.url=url;
        this.name=name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader (url,name);
        System.out.println ("下载了文件名为："+name);
    }

    public static void main(String args[]){
        TestThread2 t1 = new TestThread2("https://p0.ssl.qhimgs4.com/t014ea61b483e6a5bc6.webp","2.webp");
        TestThread2 t2 = new TestThread2("https://p0.ssl.qhimgs4.com/t014ea61b483e6a5bc6.webp","1.webp");
        TestThread2 t3 = new TestThread2("https://p0.ssl.qhimgs4.com/t014ea61b483e6a5bc6.webp","3.webp");
        t1.start();
        t2.start();
        t3.start();


    }
}


//下载器

class WebDownloader{
        //下载方法
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile (new URL (url),new File (name));
        } catch (IOException e) {
            e.printStackTrace ();
            System.out.println ("IO异常");
        }
    }
}