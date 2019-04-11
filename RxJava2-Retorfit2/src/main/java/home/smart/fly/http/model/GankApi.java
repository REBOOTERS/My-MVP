package home.smart.fly.http.model;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by engineer on 2017/12/5.
 */

public interface GankApi {
    @GET("data/Android/{param}")
    Observable<GankAndroid> getData(@Path("param") String param);


    Observable<Response<GankAndroid>> getDataResponse(@Path("param") String param);

    @GET("data/Android/{param}")
    Call<ResponseBody> getJson(@Path("param") String param);


}
