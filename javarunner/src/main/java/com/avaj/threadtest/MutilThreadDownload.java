package com.avaj.threadtest;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by rookie on 2017-03-27.
 */

public class MutilThreadDownload {
    private ThreadPoolExecutor mExecutor;

    private ExecutorService mExecutorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {

    }

    /**
     * 下载文件的子线程，每一个线程下载对应位置的文件
     *
     * @author loonggg
     */
    public static class DownloadThread extends Thread {
        private int threadId;
        private int startIndex;
        private int endIndex;
        private String path;

        /**
         * @param path       下载文件在服务器上的路径
         * @param threadId   线程id
         * @param startIndex 线程下载的开始位置
         * @param endIndex   线程下载的结束位置
         */
        public DownloadThread(String path, int threadId, int startIndex,
                              int endIndex) {
            this.path = path;
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.setRequestMethod("GET");
                // 重要：请求服务器下载部分的文件 指定文件的位置
                conn.setRequestProperty("Range", "bytes=" + startIndex + "-"
                        + endIndex);
                conn.setConnectTimeout(5000);
                // 从服务器请求全部资源的状态码200 ok 如果从服务器请求部分资源的状态码206 ok
                int code = conn.getResponseCode();
                System.out.println("---code---" + code);
                InputStream is = conn.getInputStream();// 已经设置了请求的位置，返回的是当前位置对应的文件的输入流
                RandomAccessFile raf = new RandomAccessFile("dongqiudi_website.apk", "rwd");
                // 随机写文件的时候从哪个位置开始写
                raf.seek(startIndex);// 定位文件
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    raf.write(buffer, 0, len);
                }
                is.close();
                raf.close();
                System.out.println("线程" + threadId + ":下载完毕了！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
