package com.io.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.SortedMap;

/**
 * @author zkw
 * @date 2021-04-17
 *
 * 一、通道（Channel）：用于源节点与目标节点的连接，在javanio中负责缓冲区中数据的传输。channel本身不存储数据，因此需要配合缓冲区进行传输
 * 二、通道的主要实现类
 *  java.nio.channels.Channel 接口：
 *      |--FileChannel  本地io
 *      |--SocketChannel    网络io tcp
 *      |--ServerSocketChannel 网络io tcp
 *      |--DatagramChannel 网络io udp
 * 三、获取通道
 * 1、java针对支持通道的类提供了getChannel（）方法
 *    本地IO：
 *    FileInputStream/FilelOutputStream
 *    RandomAccessFile
 *
 *    网络IO：
 *    Socket
 *    ServerSocket
 *    DatagramSocket
 *
 * 2、在jdk1.7中的nio.2针对各个通道提供了静态方法open()
 * 3、在jdk1.7中的nio.2的Files工具类的newByteChannel()
 *
 *
 *四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 *
 * 五、分散（Scatter）与聚集（Gather）
 * 分散读取（Scaattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）:将多个缓冲区中的数据聚集到通道中
 *
 * 六、字符集：Charset
 * 编码：字节串->字节数组
 * 解码：字节数组->字节串
 *
 **/
public class TestChannel {


    /**
     * 获取支持的字符集
     * @param args
     */
    public static void main(String[] args) {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        map.entrySet().forEach(entry->{
            System.out.println(entry.getKey()+"="+entry.getValue());
        });
    }

    /**
     * 分散和聚集
     * @param args
     */
    public static void main5(String[] args) throws Exception {
        RandomAccessFile raf1 = new RandomAccessFile("1.png", "rw");
        //1、获取通道
        FileChannel channel1 = raf1.getChannel();

        //2、分配指定的缓冲区的大小
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(100);
        ByteBuffer buf3 = ByteBuffer.allocate(100);

        //3、分散读取
       ByteBuffer[] bufs= {buf1,buf2,buf3};
       channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }
        //输出看看
//        System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
//        System.out.println("--------------------------------------");
//        System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));
//        System.out.println("--------------------------------------");
//        System.out.println(new String(bufs[2].array(),0,bufs[2].limit()));

        //4、聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("5.png", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);

    }


    //通道之间的数据传输(也是利用的直接缓冲区的方式)
    public static void main3(String[] args) throws Exception {
        FileChannel inchannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.png"),StandardOpenOption.READ, StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);

        //二选一 效果都是一样的
        inchannel.transferTo(0,inchannel.size(),outChannel);
//        outChannel.transferFrom(inchannel,0,inchannel.size());
        inchannel.close();
        outChannel.close();
    }


    //使用直接缓冲区模式完成文件的复制（内存映射文件）  只有ByteBuffer支持，其他Buffer不支持
    public static void main2(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println(start);
        FileChannel inchannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.png"),StandardOpenOption.READ, StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);

        //也就是我们的内存映射文件 和我们的allocateDirect()的道理一样，这里用下面的方法 和用allocateDirect是一样的效果 现在的缓冲区在物理内存中
        MappedByteBuffer inMappedBuf = inchannel.map(FileChannel.MapMode.READ_ONLY, 0, inchannel.size());
        MappedByteBuffer outMappedBuf = inchannel.map(FileChannel.MapMode.READ_WRITE, 0, inchannel.size());

        //直接对缓冲区进行数据的读写操作 不需要通道了
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        long end= System.currentTimeMillis();
        System.out.println(end-start);
        inchannel.close();
        outChannel.close();


    }


    //非直接缓冲区模式
    public static void main1(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println(start);
        //利用通道完成文件的复制
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("1.png");
            fos = new FileOutputStream("2.png");

            //1、获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //2、分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //3、将通道中的数据存入缓冲区中
            while (inChannel.read(buf)!=-1){
                //切换读取数据的模式 就是把通道中的数据读出来然后再写入缓冲区
                buf.flip();
                //4、将缓冲区中的数据写入通道中
                outChannel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            long end = System.currentTimeMillis();
            System.out.println(end-start);
            if (outChannel!=null){
                outChannel.close();
            }
            if (inChannel!=null){
                inChannel.close();
            }
            if (fis!=null){
                fis.close();
            }
            if (fos!=null){
                fos.close();
            }
        }
    }
}
