package huyifei.mymvp.views;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by rookie on 2017/1/3.
 */

public class MyView extends View {
    private static final String TAG = "MyView";

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.e(TAG, "the View clickable is " + isClickable());
    }
}
