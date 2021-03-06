package com.io.nio.testNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author zkw
 * @date 2021-04-18
 **/
public class TestNioServer {
    public static void main(String[] args) throws IOException {
        //1、获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2、切换非阻塞模式
        ssChannel.configureBlocking(false);

        //3、绑定端口连接
        ssChannel.bind(new InetSocketAddress(9898));

        //4、获取选择器
        Selector selector = Selector.open();

        //5、将通道注册到选择器中,并且指定"监听事件"（接收事件）
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);


        //6、轮巡式的获取选择器上已经“准备就绪的”事件  也就是这个SelectionKey 中有一个事件 例如SelectionKey.OP_ACCEPT事件
        while (selector.select()>0){
            // 7、获取当前选择器中所有注册的“选择建（已就绪的监听事件）”
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //8、获取准备“就绪”的事件
                SelectionKey sk = it.next();
                //9、判断具体是什么事件准备就绪
                if (sk.isAcceptable()){
                    //10、若是接收到了“接收就绪”，则获取客户端的连接
                    SocketChannel sChannel = ssChannel.accept();
                    //11、切换非阻塞模式
                    sChannel.configureBlocking(false);
                    //12、将客户端的通道注册到选择器上 OP_READ为什么是读，因为客户端可以说是写数据过来，那么我服务端就对应着读事件
                    sChannel.register(selector,SelectionKey.OP_READ);
                }else if (sk.isReadable()){
                    //13、获取当前选择器上“读就绪“状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    //14、读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len=0;
                    while ((len=sChannel.read(buf))>0){
                        buf.flip();
                        System.out.println(new String(buf.array(),0,len));
                        buf.clear();
                    }
                }
            }
            //15、处理完毕之后需要移除当前事件，要不然会重复处理
            it.remove();
        }
    }
}
