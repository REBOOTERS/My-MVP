package home.smart.fly.httpurlconnectiondemo.downloads.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
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

    public Handler mHandler;

    int startPoint;

    public OkHttpCallback(int startPoint, Handler mHandler) {
        this.startPoint = startPoint;
        this.mHandler = mHandler;
    }


    @Override
    public void onFailure(Call call, IOException e) {
//        Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call call, Response response) {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
