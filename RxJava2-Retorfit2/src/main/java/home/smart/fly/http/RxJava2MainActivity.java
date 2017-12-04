package home.smart.fly.http;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.smart.fly.http.OkHttpCache.OkHttpCacheActivity;
import home.smart.fly.http.adapter.ItemInfo;
import home.smart.fly.http.adapter.MyAdapter;

public class RxJava2MainActivity extends AppCompatActivity {
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.list)
    RecyclerView list;
    @BindView(R2.id.fab)
    FloatingActionButton fab;
    private Context mContext;

    private List<ItemInfo> demos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_rxjava2_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        demos.add(new ItemInfo(R.string.app_name, OkHttpCacheActivity.class));
        MyAdapter myAdapter = new MyAdapter(demos);
        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(myAdapter);
    }



}
