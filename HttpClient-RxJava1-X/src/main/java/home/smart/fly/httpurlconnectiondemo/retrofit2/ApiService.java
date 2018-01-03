package home.smart.fly.httpurlconnectiondemo.retrofit2;

import java.util.List;

import home.smart.fly.httpurlconnectiondemo.retrofit2.bean.GithubUserBean;
import home.smart.fly.httpurlconnectiondemo.retrofit2.bean.UserFollowerBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by rookie on 2016/11/15.
 */

public interface ApiService {

    @GET("users/{user}")
    Call<ResponseBody> getUserString(@Path("user") String user);

    @GET("users/{user}")
    Call<GithubUserBean> getUser(@Path("user") String user);


    @GET("users/{user}/followers")
    Observable<List<UserFollowerBean>> followers(@Path("user") String usr);

    /**
     * http://cybershop4-dev-restapi.dev.co-mall/api/session?login_name=15101180298&image_verifty_code=12365
     *
     * @return
     */

    @Headers({
            "channel:2",
            "os:android",
            "unique:11112"
    })
    @FormUrlEncoded
    @POST("session")
    Call<ResponseBody> login(@Field("login_name") String loginName,
                             @Field("password") String password,
                             @Field("image_verifty_code") String imageVeriftyCode);


    

}
