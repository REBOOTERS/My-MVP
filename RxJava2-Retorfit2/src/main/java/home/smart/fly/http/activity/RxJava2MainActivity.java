package home.smart.fly.http.activity;

import android.content.Context;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.hikyson.godeye.core.GodEye;
import cn.hikyson.godeye.core.internal.modules.fps.Fps;
import cn.hikyson.godeye.core.internal.modules.fps.FpsInfo;
import cn.hikyson.godeye.monitor.GodEyeMonitor;
import home.smart.fly.http.OkHttpCache.OkHttpCacheActivity;
import home.smart.fly.http.R;
import home.smart.fly.http.R2;
import home.smart.fly.http.adapter.ItemInfo;
import home.smart.fly.http.adapter.MyAdapter;
import home.smart.fly.proxy.ApiGenerator;
import home.smart.fly.proxy.LoginService;
import home.smart.fly.proxy.model.User;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * author : Rookie
 * e-mail : yingkongshi11@gmail.com
 * time   : 2017/12/06
 * desc   :
 * version: 1.0
 */
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginService loginService = ApiGenerator.generatorApi(LoginService.class);
                User user = loginService.login("123","456");

                Snackbar.make(view, user.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        demos.add(new ItemInfo(R.string.app_name, OkHttpCacheActivity.class));
        demos.add(new ItemInfo(R.string.rx_basic, RxJavaBaseActivity.class));
        demos.add(new ItemInfo(R.string.rx_operator, RxJavaOperatorActivity.class));
        demos.add(new ItemInfo(R.string.rx_lifecycle, LifeCycleActivity.class));
        MyAdapter myAdapter = new MyAdapter(demos);
        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(myAdapter);

        GodEyeMonitor.work(this);
        GodEye.instance().<Fps>getModule(GodEye.ModuleName.FPS).subject()
                .subscribe(new Consumer<FpsInfo>() {
                    @Override
                    public void accept(FpsInfo fpsInfo) throws Exception {
//                        Log.e(TAG, "accept: " + fpsInfo.currentFps);
//                        Log.e(TAG, "accept: " + fpsInfo.systemFps);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

        tempTEst();
    }

    private void tempTEst() {
        Disposable d = Observable.just("eeee")
                .filter(it->!TextUtils.isEmpty(it))
                .map(s -> 100+s.hashCode()).subscribe(integer ->
                                Log.e("zzzz", "tempTEst: " + integer),
                        throwable -> Log.e("zzzz", "accept: "+throwable.getMessage() ));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GodEyeMonitor.shutDown();
    }
}
