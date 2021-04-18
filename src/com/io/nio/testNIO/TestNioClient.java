package com.io.nio.testNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 *
 * 多人聊天室就是使用这种nio模式来实现
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
public class TestNioClient {
    public static void main(String[] args) throws IOException {
        //1、获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2、切换非阻塞模式
        sChannel.configureBlocking(false);

        //3、分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //4、发送数据给服务端
        buf.put(new Date().toString().getBytes(StandardCharsets.UTF_8));
        buf.flip();
        sChannel.write(buf);
        buf.clear();

        //5、关闭通道
        sChannel.close();
    }
}
