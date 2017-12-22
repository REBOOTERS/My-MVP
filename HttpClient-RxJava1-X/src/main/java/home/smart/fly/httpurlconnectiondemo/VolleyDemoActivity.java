package home.smart.fly.httpurlconnectiondemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rookie on 2016/11/3.
 */

public class VolleyDemoActivity extends AppCompatActivity {
    private final String BASE_URL = "https://api.github.com/";
    private final String BASE_URL_TEST = "http://cybershop4-dev-restapi.dev.co-mall/products/search";
    private Context mContext;
    private RequestQueue queue;

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        queue = Volley.newRequestQueue(mContext);
        setContentView(R.layout.activity_http_volley_demo);
        tv = (TextView) findViewById(R.id.editText);

        final StringRequest requestGithub = new StringRequest(Request.Method.GET, BASE_URL,
                new ResponseSuccessListener(), new ResponseFailListener());

        final StringRequest requestTest = new MyStringRequest(Request.Method.GET, BASE_URL_TEST,
                new ResponseSuccessListener(), new ResponseFailListener());

        findViewById(R.id.volley_github).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue.add(requestGithub);
            }
        });
        findViewById(R.id.volley_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue.add(requestTest);
            }
        });


    }


    private class MyStringRequest extends StringRequest {

        public MyStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("appkey", "ef1fc57c13007e33");
            headers.put("Accept", "application/json;charset=UTF-8");
            headers.put("merchantId", "0");
            headers.put("osVersion", "1");
            headers.put("appVersion", "1");
            headers.put("unique", "1");
            headers.put("os", "android");
            return headers;
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            HashMap<String, String> params = new HashMap<>();
            // ?pn=1&ps=12&s=1
            params.put("pn", "1");
            params.put("ps", "12");
            params.put("s", "1");
            return params;
        }
    }

    private class ResponseSuccessListener implements com.android.volley.Response.Listener<String> {

        @Override
        public void onResponse(String response) {
            tv.setText(response);
        }
    }

    private class ResponseFailListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
