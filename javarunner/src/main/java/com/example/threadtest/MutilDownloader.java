package com.example.threadtest;


import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * 多线程下载
 *
 * @author loonggg
 *
 */
public class MutilDownloader {
    // 开启的线程的个数
    public static final int THREAD_COUNT = 5;

    private static CountDownLatch mCountDownLatch = new CountDownLatch(THREAD_COUNT);

    public static void main(String[] args) throws Exception {

        ExecutorService mExecutorService = Executors.newFixedThreadPool(THREAD_COUNT);


        String path = "https://static.dongqiudi.com/app/apk/dongqiudi_website.apk";
        // 连接服务器，获取一个文件，获取文件的长度，在本地创建一个大小跟服务器文件大小一样的临时文件
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置网络请求超时时间
        conn.setConnectTimeout(5000);
        // 设置请求方式
        conn.setRequestMethod("GET");
        int code = conn.getResponseCode();
        if (code == 200) {
            // 服务器返回的数据的长度，实际就是文件的长度
            int length = conn.getContentLength();
            System.out.println("----文件总长度----" + length);
            // 在客户端本地创建出来一个大小跟服务器端文件一样大小的临时文件
            RandomAccessFile raf = new RandomAccessFile("dongqiudi_website.apk", "rwd");
            // 指定创建的这个文件的长度
            raf.setLength(length);
            // 关闭raf
            raf.close();
            // 假设是3个线程去下载资源
            // 平均每一个线程下载的文件的大小
            int blockSize = length / THREAD_COUNT;
            for (int threadId = 1; threadId <= THREAD_COUNT; threadId++) {
                // 计算每个线程下载的开始位置和结束位置
                int startIndex = (threadId - 1) * blockSize;
                int endIndex = threadId * blockSize - 1;
                if (threadId == THREAD_COUNT) {
                    endIndex = length;
                }
                System.out.println("----threadId---" + threadId
                        + "--startIndex--" + startIndex + "--endIndex--"
                        + endIndex);
                mExecutorService.execute(new DownloadThread(path, threadId, startIndex, endIndex));


            }

            mCountDownLatch.await();
            System.out.println("finish");

        }

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
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
                mCountDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
