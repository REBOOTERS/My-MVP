package huyifei.mymvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.httpurlconnectiondemo.HttpDemoActivity;
import home.smart.fly.rxandroid.RxAndroidActivity;
import home.smart.fly.rxandroid.RxJavaDemoActivity;
import huyifei.mymvp.mvp.LoginActivity;
import huyifei.mymvp.mvp.SimpleLoginActivity;
import huyifei.mymvp.util.V;

import static huyifei.mymvp.R.id.desc;

/**
 * Created by rookie on 2016/11/2.
 */

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
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
        demos.add(new ItemInfo(R.string.app_name, HttpDemoActivity.class));

    }


    private void initView() {
        swipeRefreshLayout = V.f(this, R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
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

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);
    }


    private class MyAdpater extends BaseAdapter {


        @Override
        public View getView(int index, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = View.inflate(mContext, R.layout.demo_info_item, null);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.desc = (TextView) convertView.findViewById(desc);

            holder.title.setText(demos.get(index).activitys.getSimpleName());
            holder.desc.setText(demos.get(index).desc);
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

        class Holder {
            TextView title, desc;
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


