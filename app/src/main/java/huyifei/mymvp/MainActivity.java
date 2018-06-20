package huyifei.mymvp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import home.smart.fly.http.activity.RxJava2MainActivity;
import home.smart.fly.view.MemoryLeakWithRx2Activity;
import home.smart.fly.view.MemoryLeakWithRxActivity;
import huyifei.mymvp.architecture.mvc.MVCActivity;
import huyifei.mymvp.architecture.mvp.MVPActivity;
import huyifei.mymvp.broadcastreceiver.BroadcastReceiverActivity;
import huyifei.mymvp.classloader.ClassLoaderActivity;
import huyifei.mymvp.classloader.SimpleHotFixActivity;
import huyifei.mymvp.databind.ContentActivity;
import huyifei.mymvp.datastorage.DataStorageActivity;
import huyifei.mymvp.handler.HandlerOneActivity;
import huyifei.mymvp.service.ServiceActivity;
import huyifei.mymvp.service.ThreadLocalTestActivity;
import huyifei.mymvp.service.ipc.AIDLActivity;
import huyifei.mymvp.test.HashMapActivity;
import huyifei.mymvp.util.V;
import huyifei.mymvp.memoryleak.MemoryLeakActivity;

/**
 * Created by rookie on 2016/11/2.
 */

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<ItemInfo> demos = new ArrayList<>();

    private static final String TAG = "MainActivity";
    private static final int One_M = 1024 * 1024;

    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        setData();
        initView();
        long max = Runtime.getRuntime().maxMemory();

        Log.e(TAG, "onCreate: maxMemory==" + max / One_M + " MB");


//        PrintSystemDirInfo();


        String pic1 = "https://pic4.zhimg.com/50/v2-90a75e1031331207e91827548184d18e_qhd.jpg";
        String pic2 = "https://pic1.zhimg.com/v2-7e2aef6272186b346ad468311acca63c_hd.jpg";

        String pic3 = "https://pic4.zhimg.com/10/v2-90a75e1031331207e91827548184d18e_qhd.jpg";
        String pic4 = "https://pic4.zhimg.com/0/v2-90a75e1031331207e91827548184d18e_qhd.jpg";
        String pic5 = "https://pic4.zhimg.com/100/v2-90a75e1031331207e91827548184d18e_qhd.jpg";


        printPattenr(pic1);
        printPattenr(pic2);
        printPattenr(pic3);
        printPattenr(pic4);
        printPattenr(pic5);


        printUriParams(pic1);
        printUriParams(pic2);


    }

    private void printPattenr(String url) {
        String pattern = "/[0-9]{1,2}";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(url);

        Log.e(TAG, "onCreate: pic---"+url+"----" +  "-------" + (m.find() ? m.group() : ""));

    }

    private void printUriParams(String url) {
        Uri uri = Uri.parse(url);
        Log.e(TAG, "printUriParams: host: " + uri.getHost());
        Log.e(TAG, "printUriParams: path: " + uri.getPath());
        Log.e(TAG, "printUriParams: scheme: " + uri.getScheme());
        Log.e(TAG, "printUriParams: authority " + uri.getAuthority());
        for (String str : uri.getPathSegments()) {
            Log.e(TAG, "printUriParams: params= " + str);
        }
    }


    public static String fixImageQuality(String url, String oldQuality, String newQuality) {
        String result = url;

        int indexOfQuality = url.indexOf(oldQuality);

        if (indexOfQuality > 0) {
            // 图片地址中包含有可替换的质量系数,直接替换
            result = result.replace(oldQuality, newQuality);
        } else {
            // 图片中原来没有质量系数，需要添加
            result = result.replace("com/", "com/" + newQuality + "/");
        }

        return result;
    }

    /**
     * 打印系统目录信息
     */
    private void PrintSystemDirInfo() {


        final int version = Build.VERSION.SDK_INT;
        final String mRelease = Build.VERSION.RELEASE;
        final String mSerial = Build.SERIAL;
        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        String filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
        //
        String getDataDirectory = Environment.getDataDirectory().getAbsolutePath();
        String getRootDirectory = Environment.getRootDirectory().getAbsolutePath();
        String getDownloadCacheDirectory = Environment.getDownloadCacheDirectory().getAbsolutePath();
        //
        String DIRECTORY_DCIM = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        String DIRECTORY_DOCUMENTS = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        String DIRECTORY_PICTURES = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        String DIRECTORY_DOWNLOADS = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        //
        String cacheDir = mContext.getCacheDir().getAbsolutePath();
        String filesDir = mContext.getFilesDir().getAbsolutePath();
        //
        String getExternalCacheDir = mContext.getExternalCacheDir().getAbsolutePath();
        String getExternalFilesDir_DIRECTORY_PICTURES = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        String getExternalFilesDir_DIRECTORY_DOCUMENTS = mContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();


        String[] files = new String[mContext.getExternalCacheDirs().length];
        for (int i = 0; i < mContext.getExternalCacheDirs().length; i++) {
            File mFile = mContext.getExternalCacheDirs()[i];
            files[i] = mFile.getAbsolutePath() + "\n";
        }


        Log.e(TAG, "android.os.Build.VERSION.SDK_INT = " + version);
        Log.e(TAG, "android.os.Build.VERSION.RELEASE = " + mRelease);
        Log.e(TAG, "android.os.Build.SERIAL = " + mSerial);
        Log.e(TAG, "Secure.ANDROID_ID = " + android_id);
        Log.e(TAG, "--------------------------------------------------");
        Log.e(TAG, "Environment.getExternalStorageDirectory() = " + filepath);
        Log.e(TAG, "Environment.getDataDirectory() = " + getDataDirectory);
        Log.e(TAG, "Environment.getRootDirectory() = " + getRootDirectory);
        Log.e(TAG, "Environment.getDownloadCacheDirectory() = " + getDownloadCacheDirectory);
        Log.e(TAG, "--------------------------------------------------");
        Log.e(TAG, "Environment.getExternalStorageDirectory(Environment.DIRECTORY_DCIM) = " + DIRECTORY_DCIM);
        Log.e(TAG, "Environment.getExternalStorageDirectory(Environment.DIRECTORY_DOCUMENTS) = " + DIRECTORY_DOCUMENTS);
        Log.e(TAG, "Environment.getExternalStorageDirectory(Environment.DIRECTORY_PICTURES) = " + DIRECTORY_PICTURES);
        Log.e(TAG, "Environment.getExternalStorageDirectory(Environment.DIRECTORY_DOWNLOADS) = " + DIRECTORY_DOWNLOADS);
        Log.e(TAG, "--------------------------------------------------");
        Log.e(TAG, "mContext.getCacheDir() = " + cacheDir);
        Log.e(TAG, "mContext.getFilesDir() = " + filesDir);
        Log.e(TAG, "--------------------------------------------------");
        Log.e(TAG, "mContext.getExternalCacheDir() = " + getExternalCacheDir);
        Log.e(TAG, "mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) = " + getExternalFilesDir_DIRECTORY_PICTURES);
        Log.e(TAG, "mContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) = " + getExternalFilesDir_DIRECTORY_DOCUMENTS);
        Log.e(TAG, "mContext.getExternalCacheDirs() size=" + files.length + " :" + Arrays.toString(files));
        ActivityManager mManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        int size = mManager.getMemoryClass();

        Log.e(TAG, "mManager.getMemoryClass()  应用可用内存 = " + size + " M");


    }

    private void setData() {
        demos.add(new ItemInfo(R.string.lifeCycle, LifeCycleActivity.class));
        demos.add(new ItemInfo(R.string.lifeCycle, JacksonActivity.class));
        demos.add(new ItemInfo(R.string.dataBind, ContentActivity.class));
        demos.add(new ItemInfo(R.string.classloader, ClassLoaderActivity.class));
        demos.add(new ItemInfo(R.string.hotfix, SimpleHotFixActivity.class));
        demos.add(new ItemInfo(R.string.memory_leak, MemoryLeakActivity.class));
        demos.add(new ItemInfo(R.string.memory_leak, MemoryLeakWithRxActivity.class));
        demos.add(new ItemInfo(R.string.memory_leak, MemoryLeakWithRx2Activity.class));
        demos.add(new ItemInfo(R.string.RxJava2, RxJava2MainActivity.class));
        demos.add(new ItemInfo(R.string.MVC, MVCActivity.class));
        demos.add(new ItemInfo(R.string.Mvp, MVPActivity.class));
        demos.add(new ItemInfo(R.string.GlideUse, GlideActivity.class));
        demos.add(new ItemInfo(R.string.DataStorage, DataStorageActivity.class));
        demos.add(new ItemInfo(R.string.BroadcastReceiver, BroadcastReceiverActivity.class));
        demos.add(new ItemInfo(R.string.service, ServiceActivity.class));
        demos.add(new ItemInfo(R.string.aidl, AIDLActivity.class));
        demos.add(new ItemInfo(R.string.hashmap, HashMapActivity.class));
        demos.add(new ItemInfo(R.string.threadLocal, ThreadLocalTestActivity.class));
        demos.add(new ItemInfo(R.string.handler_test, HandlerOneActivity.class));
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
            return new MyHolder(view);
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


