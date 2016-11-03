package huyifei.mymvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.rxandroid.RxAndroidActivity;
import home.smart.fly.rxandroid.RxJavaDemoActivity;
import huyifei.mymvp.mvp.LoginActivity;
import huyifei.mymvp.mvp.SimpleLoginActivity;
import huyifei.mymvp.util.V;

/**
 * Created by rookie on 2016/11/2.
 */

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private ListView listView;
    private List<ItemInfo> demos = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        setData();
        initView();
    }

    private void setData() {
        demos.add(new ItemInfo(R.string.app_name, SimpleLoginActivity.class));
        demos.add(new ItemInfo(R.string.app_name, LoginActivity.class));
        demos.add(new ItemInfo(R.string.app_name, RxJavaDemoActivity.class));
        demos.add(new ItemInfo(R.string.app_name, RxAndroidActivity.class));
    }


    private void initView() {
        listView = V.f(this, R.id.list);
        MyAdpater myAdpater = new MyAdpater();
        listView.setAdapter(myAdpater);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, demos.get(position).activitys);
                startActivity(intent);
            }
        });
    }


    private class MyAdpater extends BaseAdapter {


        @Override
        public View getView(int index, View convertView, ViewGroup parent) {
            convertView = View.inflate(mContext, R.layout.demo_info_item, null);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView desc = (TextView) convertView.findViewById(R.id.desc);

            title.setText(demos.get(index).activitys.getSimpleName());
            desc.setText(demos.get(index).desc);
            return convertView;
        }

        @Override
        public int getCount() {
            return demos.size();
        }

        @Override
        public Object getItem(int index) {
            return demos.get(index);
        }

        @Override
        public long getItemId(int id) {
            return id;
        }
    }


    private class ItemInfo {
        private final int desc;
        private final Class<? extends Activity> activitys;

        public ItemInfo(int desc, Class<? extends Activity> demoClass) {
            this.desc = desc;
            this.activitys = demoClass;
        }
    }

}


