package huyifei.mymvp.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

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


        mHashMap.put("a", "Apple");
        mHashMap.put("b", "basket");
        mHashMap.put("c", "cell");
        mHashMap.put("d", "dog");
        mHashMap.put("e", "Earth");
        mHashMap.put("f", "football");


        Log.e(TAG, "onCreate: mHashMap=" + mHashMap);

        TreeMap mTreeMap = new TreeMap();
        mTreeMap.putAll(mHashMap);

        Log.e(TAG, "onCreate: mTreeMap=" + mTreeMap);


        // 按照Value对map进行排序
        TreeMap mTreeMap1=new TreeMap(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return mHashMap.get(o1).compareTo(mHashMap.get(o2));
            }


        });

        mTreeMap1.putAll(mHashMap);
        Log.e(TAG, "onCreate: mTreeMap1=" + mTreeMap1);


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
