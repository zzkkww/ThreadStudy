package com.io.nio.testBIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/** 这是bio模式的实现 下面的注释是nio的核心原理
 * @author zkw
 * @date 2021-04-18
 * 一、使用NIO完成网络通信的三个核心
 * 1、通道（Channel）：负责连接
 *    java.nio.channels.Channel接口：
 *        |--SelectableChannel（抽象类）
 *           |--SocketChannel   实现类
 *           |--ServerSocketChannel
 *           |--DatagramChannel
 *
 *           |--Pipe.SinkChannel
 *           |--Pipe.SourceChannel
 * 2、缓冲区（Buffer）:负责数据的存取
 *
 * 3、选择器（Selector）：是SelectableChannel的多路复用器。用于监控SelectableChannel的IO的状况
 **/
public class TestBlockingBIOClient {

    /**
     * 客户端
     * @param args
     */
    public static void main(String[] args) throws IOException {
        //1、获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2、分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //3、读取本地文件，并发送到服务端
        FileChannel inchannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
        //先读进去
        while (inchannel.read(buf)!=-1){
            buf.flip();
            //最后写入通道里面
            sChannel.write(buf);
            buf.clear();
        }
        //4、关闭通道
        inchannel.close();
        sChannel.close();

    }

}
