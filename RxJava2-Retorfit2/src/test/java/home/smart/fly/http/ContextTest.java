package home.smart.fly.http;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 08-01-2019
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28, manifest = Config.NONE)
public class ContextTest {

    @Test
    public void useContextTest() {
        Context context = ApplicationProvider.getApplicationContext();
        System.out.println("context ===" + context.getClass().getName());
        assertEquals("home.smart.fly.http", context.getPackageName());
    }

}
