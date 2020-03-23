package home.smart.fly.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import androidx.annotation.RestrictTo;

import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 08-02-2019
 */
public class Utils {

    public static final boolean SDK_VERSION_LOLLIPOP_OR_LATER = VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP;

    public static final boolean SDK_VERSION_LOLLIPOP_MR1_OR_LATER = VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP_MR1;

    public static final boolean SDK_VERSION_M_OR_LATER = VERSION.SDK_INT >= VERSION_CODES.M;

    public static final boolean SDK_VERSION_N_OR_LATER = VERSION.SDK_INT >= VERSION_CODES.N;

    public static final boolean SDK_VERSION_N_MR1_OR_LATER = VERSION.SDK_INT >= VERSION_CODES.N_MR1;

    public static final boolean SDK_VERSION_O_OR_LATER = VERSION.SDK_INT >= VERSION_CODES.O;

    public static final boolean SDK_VERSION_O_MR1_OR_LATER = VERSION.SDK_INT > VERSION_CODES.O;

    private static final String MEMTOTAL_PATTERN = "MemTotal[\\s]*:[\\s]*(\\d+)[\\s]*kB\n";

    private static final String MEMFREE_PATTERN = "MemFree[\\s]*:[\\s]*(\\d+)[\\s]*kB\n";



    public static String getPackageVersionName(final Context pContext) throws NameNotFoundException {
        return getPackageInfo(pContext).versionName;
    }

    public static String getPackageName(final Context pContext) {
        return pContext.getPackageName();
    }

    synchronized private static PackageInfo getPackageInfo(final Context pContext) throws NameNotFoundException {
        final PackageManager packageManager = pContext.getPackageManager();
        return packageManager.getPackageInfo(pContext.getPackageName(), 0);
    }

    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isCharging(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        if (intent == null) {
            return false;
        }
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return plugged != 0;
    }



    public static boolean hasSystemFeature(final Context pContext, final String pFeature) {
        try {
            final Method PackageManager_hasSystemFeatures = PackageManager.class.getMethod("hasSystemFeature", String
                    .class);
            return (PackageManager_hasSystemFeatures == null) ? false : (Boolean) PackageManager_hasSystemFeatures
                    .invoke(pContext.getPackageManager(), pFeature);
        } catch (final Throwable t) {
            return false;
        }
    }

    public static int currentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }



    //region 判断手机的设备型号

    /**
     * 简单通过机型判断是不是 MIUI
     */
    public static boolean isMIUI() {
        return isCurrentPhone(Phone.XIAOMI);
    }

    /**
     * 简单通过机型判断是不是 EMUI
     */
    public static boolean isHuaweiEMUI() {
        return isCurrentPhone(Phone.HUAWEI);
    }

    /**
     * @return 是否是三星折叠屏
     */
    public static boolean isSamSungFold() {
        return isSamsung() && "SM-F9000".equalsIgnoreCase(Build.MODEL);
    }

    private static Phone sCurrentPhone = null;

    /**
     * 是否是小米手机，包括小米与 Redmi
     */
    public static boolean isXiaomi() {
        return isCurrentPhone(Phone.XIAOMI);
    }

    /**
     * 是否是 Oppo 手机
     */
    public static boolean isOppo() {
        return isCurrentPhone(Phone.OPPO);
    }

    /**
     * 是否是 Vivo 手机
     */
    public static boolean isVivo() {
        return isCurrentPhone(Phone.VIVO);
    }

    /**
     * 是否是索尼手机
     */
    public static boolean isSony() {
        return isCurrentPhone(Phone.SONY);
    }

    /**
     * 是否是华为生产的手机，包括华为与荣耀，甚至包括 Nexus 6P
     */
    public static boolean isHuawei() {
        return isCurrentPhone(Phone.HUAWEI);
    }

    /**
     * 是否是三星生产的手机
     */
    public static boolean isSamsung() {
        return isCurrentPhone(Phone.SAMSUNG);
    }

    private static boolean isCurrentPhone(Phone phone) {
        if (sCurrentPhone != null) {
            return sCurrentPhone == phone;
        }
        if (phone.isCurrent()) {
            sCurrentPhone = phone;
        }
        return sCurrentPhone == phone;
    }

    /**
     * 重置当前的 sCurrentPhone 值，用于测试
     */
    @RestrictTo(RestrictTo.Scope.TESTS)
    static void resetCurrentPhone() {
        sCurrentPhone = null;
    }

    //endregion
}
