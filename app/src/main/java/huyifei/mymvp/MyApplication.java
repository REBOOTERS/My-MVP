package huyifei.mymvp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.facebook.stetho.Stetho;

import cn.hikyson.godeye.core.GodEye;
import cn.hikyson.godeye.core.GodEyeConfig;

/**
 * Created by rookie on 2017/1/18.
 */

public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        // Normal app init code...

        Stetho.initializeWithDefaults(this);


        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                //禁止截屏
//                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
                // 保持屏幕常亮
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

        GodEye.instance().init(this);

        if (isMainProcess(this)) {//can not install modules in sub process
            // You can find assets file sample in assets path of android-godeye module
            GodEye.instance().install(GodEyeConfig.fromAssets("install.config"));
        }




    }
    /**
     * is main process
     */
    private static boolean isMainProcess(Application application) {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) application.getSystemService
                (Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return application.getPackageName().equals(processName);
    }
}
