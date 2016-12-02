package home.smart.fly.httpurlconnectiondemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by rookie on 2016/11/3.
 */

public class VolleyDemoActivity extends AppCompatActivity {
    private final String BASE_URL = "https://api.github.com/";
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

        final StringRequest request = new StringRequest(Request.Method.GET, BASE_URL,
                new ResponseSuccessListener(), new ResponseFailListener());
        findViewById(R.id.volley).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue.add(request);
            }
        });

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
