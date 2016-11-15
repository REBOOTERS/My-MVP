package home.smart.fly.httpurlconnectiondemo.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rookie on 2016/11/15.
 */

public interface GithubService {

    @GET("users/{user}")
    Call<GithubBean> getUser(@Path("user") String user);
}
