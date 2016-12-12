package home.smart.fly.httpurlconnectiondemo.retrofit2;

import java.util.List;

import home.smart.fly.httpurlconnectiondemo.retrofit2.bean.GithubUserBean;
import home.smart.fly.httpurlconnectiondemo.retrofit2.bean.UserFollowerBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by rookie on 2016/11/15.
 */

public interface GithubService {

    @GET("users/{user}")
    Call<ResponseBody> getUserString(@Path("user") String user);

    @GET("users/{user}")
    Call<GithubUserBean> getUser(@Path("user") String user);


    @GET("users/{user}/followers")
    Observable<List<UserFollowerBean>> followers(@Path("user") String usr);

}
