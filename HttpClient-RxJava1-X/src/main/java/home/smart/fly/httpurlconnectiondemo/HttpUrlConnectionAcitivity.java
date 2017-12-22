package home.smart.fly.httpurlconnectiondemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rookie on 2016/11/3.
 */

public class HttpUrlConnectionAcitivity extends AppCompatActivity {
    private TextView tv;
    private Button button;

    private final String BASE_URL = "https://www.baidu.com";

    private final String BASE_URL_TEST = "http://cybershop4-dev-restapi.dev.co-mall/products/search";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection_demo);
        button = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute(BASE_URL_TEST);
            }
        });
    }


    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            InputStream mInputStream;
            HttpURLConnection connection = getHttpUrlConnection(params[0]);
            StringBuilder result = new StringBuilder();
            try {
                int statusCode = connection.getResponseCode();
                String response = connection.getResponseMessage();
                mInputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(mInputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                result.append("StatusCode: ").append(statusCode).append("\n").
                        append("Response: ").append(response).append("\n").
                        append(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }
    }


    private HttpURLConnection getHttpUrlConnection(String url) {
        HttpURLConnection connection = null;
        try {
            URL mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(40000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Charset", "utf-8");
            connection.setRequestProperty("Content-Length", "0");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }


}
