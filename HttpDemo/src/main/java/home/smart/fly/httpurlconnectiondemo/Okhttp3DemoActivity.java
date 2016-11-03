package home.smart.fly.httpurlconnectiondemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by rookie on 2016/11/3.
 * <p>
 * okhttp 3 异步请求的回调不在UI 线程当中
 */

public class Okhttp3DemoActivity extends AppCompatActivity {
    private final String BASE_URL = "https://www.baidu.com";
    private Context mContext;

    private TextView tv;
    private ProgressBar loading;

    private OkHttpClient client;
    private Request request;
    private MyHandler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        handler = new MyHandler();

        setContentView(R.layout.activity_okhttp_three_demo);
        tv = (TextView) findViewById(R.id.editText);
        loading = (ProgressBar) findViewById(R.id.loading);


        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                loading.setVisibility(View.VISIBLE);
                client = new OkHttpClient();
                Request.Builder builder = new Request.Builder()
                        .url(BASE_URL)
                        .method("GET", null);

                request = builder.build();
                Call mCall = client.newCall(request);
                mCall.enqueue(new MyCallback());

            }
        });

        findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                loading.setVisibility(View.VISIBLE);

                client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
//                        .add("page", "1")
                        .build();
                request = new Request.Builder()
                        .url(BASE_URL)
                        .post(formBody)
                        .build();
                Call mCall = client.newCall(request);
                mCall.enqueue(new MyCallback());
            }
        });

        findViewById(R.id.downloadFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
                    client = new OkHttpClient();
                    request = new Request.Builder()
                            .url(BASE_URL)
                            .build();
                    Call mCall = client.newCall(request);
                    mCall.enqueue(new DownloadCallback());
                }




            }
        });

    }

    private class MyCallback implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {
            Message msg = new Message();
            msg.what = 100;
            msg.obj = e;
            handler.sendMessage(msg);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Message msg = new Message();
            msg.what = 200;
            msg.obj = response.body().string();
            handler.sendMessage(msg);
        }
    }

    private class DownloadCallback implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String path=Environment.getExternalStorageDirectory().getPath();
            File file = new File(path, "test.apk");
            FileOutputStream fileOutputStream;
            InputStream inputStream;
            inputStream = response.body().byteStream();
            fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[2048];

            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.flush();

        }
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            loading.setVisibility(View.GONE);
            switch (msg.what) {
                case 100:
                    Object e = msg.obj;
                    Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                    break;
                case 200:
                    String response = (String) msg.obj;
                    tv.setText(response);


                    break;
                default:
                    break;
            }
        }
    }


}
