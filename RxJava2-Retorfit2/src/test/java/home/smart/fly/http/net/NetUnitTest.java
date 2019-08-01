package home.smart.fly.http.net;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.TimeUnit;

import home.smart.fly.http.model.GankAndroid;
import home.smart.fly.http.model.GankApi;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.stetho.server.http.HttpStatus.HTTP_OK;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 08-01-2019
 *
 * 参考资料 @link {https://blog.csdn.net/qq_17766199/article/details/78881992}
 *
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28, manifest = Config.NONE)
public class NetUnitTest {

    private static final String BASE_URL = "http://gank.io/api/";


    @Rule
    public RxJavaRule mRxJavaRule = new RxJavaRule();

    @Test
    public void netTest() {
        Context context = ApplicationProvider.getApplicationContext();

        final Retrofit mRetrofit = initRetrofit();

        GankApi mGankApi = mRetrofit.create(GankApi.class);
        Observable<GankAndroid> mAndroidObservable = mGankApi.getData("10/1");
        mAndroidObservable.subscribe(new Consumer<GankAndroid>() {
            @Override
            public void accept(GankAndroid gankAndroid) {
                System.out.println("success");
                System.out.println();
                System.out.println("response " + gankAndroid.getResults().get(0));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                System.out.println("fail");
            }
        });
    }


    @Test
    public void netWithInterceptTest() {
        String json = "404";
        SimpleIntercept errorIntercept = new SimpleIntercept(json, 404);

        SimpleIntercept successIntercept = new SimpleIntercept(json, HTTP_OK);


        final Retrofit mRetrofit = initRetrofit(errorIntercept);

        GankApi mGankApi = mRetrofit.create(GankApi.class);
        Observable<GankAndroid> mAndroidObservable = mGankApi.getData("10/1");
        mAndroidObservable.subscribe(new Consumer<GankAndroid>() {
            @Override
            public void accept(GankAndroid gankAndroid) {
                System.out.println("success");
                System.out.println();
                System.out.println("response " + gankAndroid.getResults().get(0));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                System.out.println("fail " + throwable.getMessage());
            }
        });
    }


    private Retrofit initRetrofit(Interceptor... interceptors) {
        final OkHttpClient mClient = initOkHttpClient(interceptors);

        final Retrofit mRetrofit = new Retrofit.Builder()
                .client(mClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return mRetrofit;
    }

    private OkHttpClient initOkHttpClient(Interceptor... interceptors) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));


        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }

        return builder.build();
    }
}
