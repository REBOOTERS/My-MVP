package home.smart.fly.httpurlconnectiondemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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
    private static final String TAG = "Okhttp3DemoActivity";

    private final String BASE_URL = "https://api.github.com/";
    private final String DOWNLOAD_URL = "http://dl.bizhi.sogou.com/images/2015/06/26/1214911.jpg";
    private final String FILE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "test.jpg";
    private Context mContext;

    private TextView tv;
    private ProgressBar loading;
    private ImageView imageView;

    private OkHttpClient client;
    private Request request;
    private MyHandler handler;

    private ProgressDialog progressDialog;
    private Call downloadCall;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        handler = new MyHandler();


        setContentView(R.layout.activity_okhttp_three_demo);
        tv = (TextView) findViewById(R.id.editText);
        loading = (ProgressBar) findViewById(R.id.loading);
        imageView = (ImageView) findViewById(R.id.image);


        progressDialog = new ProgressDialog(mContext);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
                downloadCall.cancel();
            }
        });
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("下载文件");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


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


        findViewById(R.id.post1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                loading.setVisibility(View.VISIBLE);

                client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("login_name", "15101180298")
                        .add("image_verifty_code", "1236")
                        .add("password", "123456")
                        .build();
                request = new Request.Builder()
                        .url("http://cybershop4-dev-restapi.dev.co-mall/api/session")
                        .addHeader("channel", "2")
                        .addHeader("os","android")
                        .addHeader("unique","11112")
                        .post(formBody)
                        .build();
                Call mCall = client.newCall(request);
                mCall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onFailure: e=" + e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e(TAG, "onResponse: response=" + response);
                        if (response.code() == 201) {
//                            Log.e(TAG, "onResponse: body==" + response.body().string());
                            String body=response.body().string();
                            Log.e(TAG, "onResponse: body="+body );
                            Message msg = new Message();
                            msg.what = 200;
                            msg.obj = body;
                            handler.sendMessage(msg);
                        }
                    }
                });
            }
        });

        /**
         * http://cybershop4-dev-restapi.dev.co-mall/api/members/members_created_by_mobile
         * ?phone_number=15101180298&password=123456&sms_verify_code=1234&captcha=1234
         */

        findViewById(R.id.post2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client = new OkHttpClient();


                FormBody mFormBody = new FormBody.Builder()
                        .add("phone_number", "15101180298")
                        .add("password", "123456")
                        .add("sms_verify_code", "1234")
                        .add("captcha", "1234")
                        .build();

                request = new Request.Builder()
                        .url("http://cybershop4-dev-restapi.dev.co-mall/api/members/members_created_by_mobile")
                        .post(mFormBody)
                        .build();

                Call mCall = client.newCall(request);
                mCall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onFailure: e=" + e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e(TAG, "onResponse: response=" + response);
                    }
                });
            }
        });


        findViewById(R.id.post3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client=new OkHttpClient();

                FormBody mFormBody=new FormBody.Builder()
                        .add("quantity","1")
                        .build();

                request=new Request.Builder()
                        .url("http://cybershop4-dev-restapi.dev.co-mall/api/carts/mine/baskets/items?goods_id=30055")
                        .addHeader("channel", "2")
                        .addHeader("os","android")
                        .addHeader("unique","11112")
                        .addHeader("userSession","72D212F46592CC833B55A94D6A683E5D")
                        .post(mFormBody)
                        .build();

                Call mCall = client.newCall(request);
                mCall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "onFailure: e=" + e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e(TAG, "onResponse: response=" + response);
                    }
                });


            }
        });

        findViewById(R.id.downloadFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    client = new OkHttpClient();
                    request = new Request.Builder()
                            .url(DOWNLOAD_URL)
                            .build();
                    downloadCall = client.newCall(request);
                    downloadCall.enqueue(new DownloadCallback());
                }


            }
        });

    }

    private class MyCallback implements Callback {

        @Override
        public void onFailure(@NonNull Call call, IOException e) {
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
            File file = new File(FILE_PATH);
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
                handler.sendMessage(msg);
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
                case 300:
                    int percent = msg.arg1;
                    Log.e("llll", "the percent is " + percent);
                    if (percent < 100) {
                        progressDialog.setProgress(percent);
                    } else {
                        progressDialog.dismiss();
                        Glide.with(mContext).load(FILE_PATH).into(imageView);
                    }
                    break;
                default:
                    break;
            }
        }
    }


}
