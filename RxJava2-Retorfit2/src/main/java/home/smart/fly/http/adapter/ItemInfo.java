package home.smart.fly.http.adapter;

import android.app.Activity;

/**
 * Created by engineer on 2017/12/4.
 */

public class ItemInfo {
    public final int desc;
    public final Class<? extends Activity> activitys;

    public ItemInfo(int desc, Class<? extends Activity> demoClass) {
        this.desc = desc;
        this.activitys = demoClass;
    }
}
