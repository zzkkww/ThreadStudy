package com.io.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**1、缓存区（Buffer）:在java nio中负责数据的存取。缓冲区就是数组。用于不同数据类型的数据
 *  根据数据类型不同（boolean除外），提供了相应类型的缓冲区：
 *  ByteBuffer
 *  CharBuffer
 *  ShortBuffer
 *  IntBuffer
 *  LongBuffer
 *  FloatBuffer
 *  DoubleBuffer
 *
 *  上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区
 *
 *  二、缓冲区存取数据的两个核心方法
 *  put（）：存入数据到缓冲区
 *  get():获取缓冲区中的数据
 *
 *
 *  四、缓冲区中的四个核心属性
 *  capacity：容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变  缓冲区底层就是数组
 *  limit:界限，表示缓冲区中可以操作数据的大小。（limit后数据不能进行读写）
 *  position：位置，表示缓冲区中正在操作数据的位置
 *
 *
 *  mark:标记，表示记录当前position的位置。可以通过rest()回复到mark的位置
 *
 *  0<=mark<=position<=limit<=capacity
 *
 *  五、直接缓冲区域非直接缓冲区：
 *  非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在jvm的内存中
 *  直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在操作系统的物理内存中。可以提高效率
 *
 * @author zkw
 * @date 2021-04-14
 **/
public class TestBuffer {


    public static void main(String[] args) {
        //分配直接缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

        //判断是否是直接缓冲区
        System.out.println(buf.isDirect());
    }

    public static void main1(String[] args) {
        String str="abcde";

        //1、分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("--------------------allocate()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //2、利用put()存入数据到缓冲区中
        buf.put(str.getBytes(StandardCharsets.UTF_8));

        System.out.println("--------------------put()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

//        byte b = buf.get(1);
//        System.out.println(b);

        //3、切换到读取数据的模式
        buf.flip();
        System.out.println("--------------------flip()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4、利用get（）读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst,0, dst.length));

        System.out.println("--------------------get()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5、rewind() 可重复读数据
        buf.rewind();
        System.out.println("--------------------rewind()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //6、clear() 清空缓冲区,但是缓冲区中的数据依然存在，但是出于一种“被遗忘”的状态 就是说 limit position都不指向那个数据了，但是数据还是存在的
        buf.clear();
        System.out.println("--------------------clear()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println((char) buf.get());

    }

    public static void main2(String[] args) {
        String str="abcde";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());

        buf.flip();

        byte[] dst = new byte[buf.limit()];

        buf.get(dst,0,2);
        System.out.println(new String(dst,0, 2));
        System.out.println(buf.position());

        //mark()标记
        buf.mark();
        buf.get(dst,2,2);
        System.out.println(new String(dst,2, 2));
        System.out.println(buf.position());

        //rest()：h获取到mark的位置
        buf.reset();
        System.out.println(buf.position());

        //判断缓冲区中是否还有剩余的数据
        if (buf.hasRemaining()){
            //获取缓冲区中可以操作的数量
            System.out.println(buf.remaining());
        }

    }
}
