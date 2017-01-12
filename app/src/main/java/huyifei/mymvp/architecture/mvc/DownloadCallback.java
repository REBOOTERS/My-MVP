package huyifei.mymvp.architecture.mvc;

import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import huyifei.mymvp.architecture.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by rookie on 2017/1/12.
 */

public class DownloadCallback implements Callback {

    private Handler mHandler;

    public DownloadCallback(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        File file = new File(Constants.FILE_PATH);
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        inputStream = response.body().byteStream();
        fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[2048];

        long fileSize = response.body().contentLength();
        long temp = 0;

        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
            temp = temp + len;
            int percent = (int) (temp * 100.0f / fileSize);
            Message msg = new Message();
            msg.what = 300;
            msg.arg1 = percent;
            mHandler.sendMessage(msg);
        }
        fileOutputStream.flush();

    }
}
