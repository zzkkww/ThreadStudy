package com.io.nio.testBIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author zkw
 * @date 2021-04-18
 **/
public class TestBlockingBIOServer {
    public static void main(String[] args) throws IOException {
        //1、获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2、绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        //3、获取客户端连接的通道  accept()是阻塞的方法，只有有客户端连接下来了才会走到下面的代码
        //否则一直阻塞在这一行
        SocketChannel sChannel = ssChannel.accept();

        //4、接收客户端的数据，并保存到本地
        FileChannel outChannel = FileChannel.open(Paths.get("8.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (sChannel.read(buf)!=-1){//注意read也是阻塞的方法，如果没有数据的话会一直阻塞在这里
            buf.flip();
            //从通道中把数据写出
            outChannel.write(buf);
            buf.clear();
        }

        //5、关闭通道
        sChannel.close();
        outChannel.close();
        ssChannel.close();
    }
}
