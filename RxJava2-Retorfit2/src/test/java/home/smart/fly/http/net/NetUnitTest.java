package home.smart.fly.http.net;

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
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

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

        final Retrofit mRetrofit = initRetrofit();

        GankApi mGankApi = mRetrofit.create(GankApi.class);
        Observable<GankAndroid> mAndroidObservable = mGankApi.getData("10/1");
        mAndroidObservable.subscribe(gankAndroid -> {
            doAssert(gankAndroid.getResults().get(0));
        }, throwable -> System.out.println("fail"));
    }

    private void doAssert(GankAndroid.ResultsEntity entity) {
        assertEquals("5d423ff19d2122031ea52264", entity.get_id());
        assertEquals("web", entity.getSource());
        assertEquals("Android", entity.getType());
        assertEquals("潇湘剑雨", entity.getWho());
    }


    @Test
    public void netWithInterceptTest() {
        String json = "404";
        SimpleIntercept errorIntercept = new SimpleIntercept(json, 404);


//        SimpleIntercept successIntercept = new SimpleIntercept(json, HTTP_OK);

        final Retrofit mRetrofit = initRetrofit(errorIntercept);

        GankApi mGankApi = mRetrofit.create(GankApi.class);
        Observable<GankAndroid> mAndroidObservable = mGankApi.getData("10/1");
        mAndroidObservable.subscribe(gankAndroid -> {
                },
                throwable -> {
                    // for example
                    assertEquals("HTTP 404 404", throwable.getMessage());
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
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY));


        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }

        return builder.build();
    }
}
