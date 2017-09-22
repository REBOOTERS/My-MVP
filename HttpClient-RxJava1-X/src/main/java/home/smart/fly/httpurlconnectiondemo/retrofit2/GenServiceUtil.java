package home.smart.fly.httpurlconnectiondemo.retrofit2;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rookie on 2016/11/15.
 */

public class GenServiceUtil {

    private static final String BASE_URL = "https://api.github.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.client(httpClient.build()).build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }


}
