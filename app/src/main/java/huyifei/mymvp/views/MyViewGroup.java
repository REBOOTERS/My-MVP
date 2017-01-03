package huyifei.mymvp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by rookie on 2017/1/3.
 */

public class MyViewGroup extends LinearLayout {
    private static final String TAG = "MyViewGroup";

    public MyViewGroup(Context context) {
        super(context);
        init();
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        Log.e(TAG, "MyViewGroup clickable is " + isClickable());
    }
}
