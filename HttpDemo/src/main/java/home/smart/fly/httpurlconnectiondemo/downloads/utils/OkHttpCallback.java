package home.smart.fly.httpurlconnectiondemo.downloads.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by engineer on 2017/3/25.
 */

public class OkHttpCallback implements Callback {

    private Handler mHandler;

    private int startPoint;

    public OkHttpCallback(int startPoint, Handler mHandler) {
        this.startPoint = startPoint;
        this.mHandler = mHandler;
    }


    @Override
    public void onFailure(Call call, IOException e) {
        mHandler.sendEmptyMessage(100);
    }

    @Override
    public void onResponse(Call call, Response response) {

        if (response.code() != HttpURLConnection.HTTP_PARTIAL) {
            //返回code非206 ，不支持断点续传
            mHandler.sendEmptyMessage(400);
            return;
        }


        FileChannel fileChannel = null;
        ResponseBody body = response.body();
        int total = (int) body.contentLength();
        int currentLength = 0;
        InputStream inputStream = body.byteStream();

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(Constants.FILE_PATH, "rws");
            fileChannel = randomAccessFile.getChannel();
            Log.e(TAG, "onResponse: startPoint=" + startPoint + " ,total=" + total);
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, startPoint, total);
            int len;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {

                currentLength = currentLength + len;
                mappedByteBuffer.put(buffer, 0, len);

                Message msg = Message.obtain();
                msg.arg1 = total;
                msg.arg2 = currentLength;
                msg.what = 300;
                mHandler.sendMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                if (fileChannel != null) {
                    fileChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
