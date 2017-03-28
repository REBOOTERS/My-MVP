package com.example.threadtest;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by rookie on 2017-03-23.
 */

public class RandomIoDemo {

    private static int len;

    public static void main(String[] args) throws Exception {
        // 预分配文件所占的磁盘空间，磁盘中会创建一个指定大小的文件
        RandomAccessFile raf = new RandomAccessFile("result.txt", "rwd");
        raf.setLength(1024); // 预分配 1kb 的文件空间
        raf.close();

        // 所要写入的文件内容
        String s1 = "第一个字符串的内容";
        String s2 = "第二个字符串的内容";
        String s3 = "第三个字符串的内容";
        String s4 = "第四个字符串的内容";
        String s5 = "第五个字符串的内容";

        len = s1.getBytes().length;


        // 利用多线程同时写入一个文件

        new FileWriteThread(0, s1.getBytes()).start();
        new FileWriteThread(len, s2.getBytes()).start();
        new FileWriteThread(len * 2, s3.getBytes()).start();
        new FileWriteThread(len * 3, s4.getBytes()).start();
        new FileWriteThread(len * 4, s5.getBytes()).start();
    }

    // 利用线程在文件的指定位置写入指定数据
    private static class FileWriteThread extends Thread {
        private int skip;
        private byte[] content;

        private FileWriteThread(int skip, byte[] content) {
            this.skip = skip;
            this.content = content;
        }

        public void run() {
            try {
                FileChannel channel = new RandomAccessFile("result.txt", "rwd").getChannel();
                MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, skip, len);
                buffer.put(content);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
