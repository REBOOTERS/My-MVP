package home.smart.fly.http;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import home.smart.fly.utils.Utils;

import static org.junit.Assert.assertEquals;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 08-01-2019
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28, manifest = Config.NONE)
public class ContextTest {

    @Test
    public void useContextTest() throws Utils.SystemUtilsException {
        Context context = ApplicationProvider.getApplicationContext();

        System.out.println("context ===" + context.getClass().getName());

        assertEquals(Constants.PACKAGE_NAME, Utils.getPackageName(context));
        assertEquals(Constants.PACKAGE_VERSION, Utils.getPackageVersionName(context));
    }

    @Test
    public void screenInfoTest() {
        Context context = ApplicationProvider.getApplicationContext();
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        float density = displayMetrics.density;
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;

        System.out.println("density==" + density);
        System.out.println("width  ==" + width);
        System.out.println("height ==" + height);
    }

}
