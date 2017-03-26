package com.example.threadtest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by rookie on 2017-03-23.
 */

public class Test {

    public static void main(String[] args) throws Exception {
        // 预分配文件所占的磁盘空间，磁盘中会创建一个指定大小的文件
        RandomAccessFile raf = new RandomAccessFile("abc.txt", "rwd");
        raf.setLength(1024 * 1024); // 预分配 1M 的文件空间
        raf.close();

        // 所要写入的文件内容
        String s1 = "第一个字符串";
        String s2 = "第二个字符串";
        String s3 = "第三个字符串";
        String s4 = "第四个字符串";
        String s5 = "第五个字符串";


        System.out.println("the s1.getBytes' length " + s1.getBytes().length);

        // 利用多线程同时写入一个文件

        new FileWriteThread(0, s1.getBytes()).start();
        new FileWriteThread(18, s2.getBytes()).start();
        new FileWriteThread(36, s3.getBytes()).start();
        new FileWriteThread(54, s4.getBytes()).start();
        new FileWriteThread(72, s5.getBytes()).start();
    }

    // 利用线程在文件的指定位置写入指定数据
    static class FileWriteThread extends Thread {
        private int skip;
        private byte[] content;

        public FileWriteThread(int skip, byte[] content) {
            this.skip = skip;
            this.content = content;
        }

        public void run() {
            RandomAccessFile raf = null;
            try {
                raf = new RandomAccessFile("abc.txt", "rwd");
                raf.seek(skip);
                raf.write(content);
                System.out.println("the length is " + raf.getFilePointer());
                raf.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    raf.close();
                } catch (Exception e) {
                }
            }
        }
    }

}
