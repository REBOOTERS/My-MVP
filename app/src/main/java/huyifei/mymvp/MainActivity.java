package huyifei.mymvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.networkbench.agent.impl.NBSAppAgent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import home.smart.fly.http.activity.RxJava2MainActivity;
import home.smart.fly.httpurlconnectiondemo.HttpDemoActivity;
import home.smart.fly.httpurlconnectiondemo.RxJavaDemoActivity;
import home.smart.fly.httpurlconnectiondemo.retrofit2.Retrofit2DemoActivity;
import home.times.designpatterns.DesignPatternActivity;
import huyifei.mymvp.architecture.mvc.MVCActivity;
import huyifei.mymvp.architecture.mvp.MVPActivity;
import huyifei.mymvp.broadcastreceiver.BroadcastReceiverActivity;
import huyifei.mymvp.classloader.ClassLoaderActivity;
import huyifei.mymvp.classloader.SimpleHotFixActivity;
import huyifei.mymvp.datastorage.DataStorageActivity;
import huyifei.mymvp.service.ServiceActivity;
import huyifei.mymvp.test.HashMapActivity;
import huyifei.mymvp.util.V;

/**
 * Created by rookie on 2016/11/2.
 */

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ItemInfo> demos = new ArrayList<>();

    private Toolbar toolbar;

    AsyncTask mAsyncTask;
    InvocationHandler m;
    Proxy mProxy;

    Handler mHandler;


    ArrayList mArrayList;
    Deque mDeque;
    Stack mStack;
    Vector mVector;
    LinkedList mLinkedList;
    Comparable mComparable;

    ClassLoader mClassLoader;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        setData();
        initView();

        NBSAppAgent
                .setLicenseKey("52d5f19f17d849d1b7cf04dc686969dd")
                .withLocationServiceEnabled(true)
                .start(this.getApplicationContext());
    }


    private void setData() {
        demos.add(new ItemInfo(R.string.classloader, LifeCycleActivity.class));
        demos.add(new ItemInfo(R.string.classloader, ClassLoaderActivity.class));
        demos.add(new ItemInfo(R.string.hotfix, SimpleHotFixActivity.class));
        demos.add(new ItemInfo(R.string.service, MemoryLeakActivity.class));
        demos.add(new ItemInfo(R.string.simpleRxJava, RxJavaDemoActivity.class));
        demos.add(new ItemInfo(R.string.AndroidHttp, HttpDemoActivity.class));
        demos.add(new ItemInfo(R.string.Retrofit, Retrofit2DemoActivity.class));
        demos.add(new ItemInfo(R.string.RxJava2, RxJava2MainActivity.class));
        demos.add(new ItemInfo(R.string.MVC, MVCActivity.class));
        demos.add(new ItemInfo(R.string.Mvp, MVPActivity.class));
        demos.add(new ItemInfo(R.string.design, DesignPatternActivity.class));
        demos.add(new ItemInfo(R.string.GlideUse, GlideActivity.class));
        demos.add(new ItemInfo(R.string.DataStorage, DataStorageActivity.class));
        demos.add(new ItemInfo(R.string.BroadcastReceiver, BroadcastReceiverActivity.class));
        demos.add(new ItemInfo(R.string.service, ServiceActivity.class));
        demos.add(new ItemInfo(R.string.service, HashMapActivity.class));
    }


    private void initView() {
        toolbar = V.f(this, R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = V.f(this, R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        recyclerView = V.f(this, R.id.list);
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(myAdapter);

        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
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


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


        public MyAdapter() {

        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.demo_info_item, null);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            holder.title.setText(demos.get(position).activitys.getSimpleName());
            holder.desc.setText(demos.get(position).desc);
            holder.itemshell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, demos.get(position).activitys);
                    startActivity(intent);
                }
            });
        }


        @Override
        public long getItemId(int id) {
            return id;
        }

        @Override
        public int getItemCount() {
            return demos.size();
        }


        class MyHolder extends RecyclerView.ViewHolder {
            TextView title, desc;
            LinearLayout itemshell;

            public MyHolder(View itemView) {
                super(itemView);
                title = V.f(itemView, R.id.title);
                desc = V.f(itemView, R.id.desc);
                itemshell = V.f(itemView, R.id.itemshell);
            }
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


