package huyifei.mymvp.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;

import huyifei.mymvp.R;

public class HashMapActivity extends AppCompatActivity {
    private static final String TAG = "HashMapActivity";
    private HashMap<String, String> mHashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_map);

        mHashMap = new HashMap<>();

        int hash = "name".hashCode() ^ "name".hashCode() >>> 16;

        Log.e(TAG, "onCreate: hash=" + hash);

        mHashMap.put("name", "mike");

    }
}
