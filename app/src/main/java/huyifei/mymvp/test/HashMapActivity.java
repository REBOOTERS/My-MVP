package huyifei.mymvp.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import huyifei.mymvp.R;

public class HashMapActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HashMapActivity";
    private HashMap<String, String> mHashMap;


    private EditText mKey, mValue;
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_map);

        mHashMap = new HashMap<>();


        Button mPut = findViewById(R.id.put);
        mPut.setOnClickListener(this);
        Button mGet = findViewById(R.id.get);
        mGet.setOnClickListener(this);
        Button mRemove = findViewById(R.id.remove);
        mRemove.setOnClickListener(this);
        mKey = findViewById(R.id.key);
        mValue = findViewById(R.id.value);
        mResult = findViewById(R.id.result);

    }

    @Override
    public void onClick(View view) {
        String result;
        String key = mKey.getText().toString().trim();
        String value = mValue.getText().toString().trim();
        switch (view.getId()) {
            case R.id.put:
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    mHashMap.put(key, value);
                }
                break;
            case R.id.get:
                if (!TextUtils.isEmpty(key)) {
                    result = mHashMap.get(key);
                    mResult.setText(result);
                }

                break;
            case R.id.remove:
                if (!TextUtils.isEmpty(key)) {
                    result = mHashMap.remove(key);
                    Toast.makeText(this, result + " has been removed !", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
