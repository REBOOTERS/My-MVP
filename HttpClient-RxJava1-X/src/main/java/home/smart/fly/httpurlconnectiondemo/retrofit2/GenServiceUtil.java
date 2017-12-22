package home.smart.fly.httpurlconnectiondemo.retrofit2;

import android.os.Environment;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rookie on 2016/11/15.
 */

public class GenServiceUtil {
    // 缓存10MB
    private static final long cacheSize = 1024 * 1024 * 10;
    // 缓存路劲
    private static final String cacheDirectory = Environment.getExternalStorageDirectory() + File.separator + "GithubCache";
    //
    private static final Cache cache = new Cache(new File(cacheDirectory), cacheSize);

    private static final String BASE_URL = "https://api.github.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().cache(cache);


    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.client(httpClient.build()).build();


    public static <S> S createService(Class<S> serviceClass) {
        checkCacheDirectory();
        return retrofit.create(serviceClass);
    }

    private static void checkCacheDirectory() {
        File mFile = new File(cacheDirectory);
        if (!mFile.exists()) {
            mFile.mkdir();
        }
    }


}
