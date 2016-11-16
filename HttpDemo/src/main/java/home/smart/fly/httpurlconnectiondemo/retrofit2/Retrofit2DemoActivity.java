package home.smart.fly.httpurlconnectiondemo.retrofit2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import home.smart.fly.httpurlconnectiondemo.R;
import home.smart.fly.httpurlconnectiondemo.retrofit2.bean.GithubUserBean;
import home.smart.fly.httpurlconnectiondemo.retrofit2.bean.UserFollowerBean;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by rookie on 2016/11/3.
 * <p>
 * Retrofit2.0 使用
 */

public class Retrofit2DemoActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = Retrofit2DemoActivity.class.getSimpleName();
    private final String BASE_URL = "https://api.github.com/";
    private final String DOWNLOAD_URL = "http://dl.bizhi.sogou.com/images/2015/06/26/1214911.jpg";
    private final String FILE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "test.jpg";
    private Context mContext;


    private ProgressDialog loading;
    private LinearLayout viewShell;
    private EditText username;
    private String name;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;


        setContentView(R.layout.activity_retrofit_demo);
        viewShell = (LinearLayout) findViewById(R.id.viewShell);
        username = (EditText) findViewById(R.id.username);
        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.get1).setOnClickListener(this);
        findViewById(R.id.get2).setOnClickListener(this);
        findViewById(R.id.get3).setOnClickListener(this);
        findViewById(R.id.get4).setOnClickListener(this);

        loading = new ProgressDialog(mContext);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.setIndeterminate(true);
        loading.setInverseBackgroundForced(true);
        loading.setMessage("loading...");

    }

    private void SimpleRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL);
        Retrofit retrofit = builder.client(httpClient.build()).build();
        GithubService simpleService = retrofit.create(GithubService.class);
        Call<ResponseBody> call = simpleService.getUserString(name);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loading.dismiss();
                try {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    GithubUserBean bean = gson.fromJson(result, GithubUserBean.class);
                    setUserInfo(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loading.dismiss();
            }
        });
    }

    private void LazyRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();
        GithubService service = retrofit.create(GithubService.class);
        Call<GithubUserBean> call = service.getUser(name);
        call.enqueue(new Callback<GithubUserBean>() {
            @Override
            public void onResponse(Call<GithubUserBean> call, Response<GithubUserBean> response) {
                GithubUserBean bean = response.body();
                setUserInfo(bean);
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<GithubUserBean> call, Throwable t) {
                loading.dismiss();
            }
        });
    }

    private void EasyRetrofit() {
        GithubService service = GenServiceUtil.createService(GithubService.class);
        Call<GithubUserBean> call = service.getUser(name);
        call.enqueue(new Callback<GithubUserBean>() {
            @Override
            public void onResponse(Call<GithubUserBean> call, Response<GithubUserBean> response) {
                GithubUserBean bean = response.body();
                loading.dismiss();
                setUserInfo(bean);
            }

            @Override
            public void onFailure(Call<GithubUserBean> call, Throwable t) {
                loading.dismiss();
            }
        });
    }

    private void RxRetrofit() {
        GithubService service = GenServiceUtil.createService(GithubService.class);
        final Call<GithubUserBean> call = service.getUser(name);
        final Observable myObserable = Observable.create(new Observable.OnSubscribe<GithubUserBean>() {
            @Override
            public void call(Subscriber<? super GithubUserBean> subscriber) {
                Response<GithubUserBean> bean = null;
                try {
                    bean = call.execute();
                    subscriber.onNext(bean.body());

                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }

                subscriber.onCompleted();
            }
        });


        myObserable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GithubUserBean, GithubUserBean>() {
                    @Override
                    public GithubUserBean call(GithubUserBean o) {
                        if (TextUtils.isEmpty(o.getBio())) {
                            o.setBio("nothing !");
                        }
                        return o;
                    }
                })
                .subscribe(new Subscriber<GithubUserBean>() {
                    @Override
                    public void onCompleted() {
                        loading.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.dismiss();
                    }

                    @Override
                    public void onNext(GithubUserBean o) {
                        setUserInfo(o);
                    }
                });

    }


    private void RxRetrofitList() {
        GithubService service = GenServiceUtil.createService(GithubService.class);
        final Call<List<UserFollowerBean>> call = service.getFollowers(name);

        Observable<List<UserFollowerBean>> myObserve = Observable.create(new Observable.OnSubscribe<List<UserFollowerBean>>() {
            @Override
            public void call(Subscriber<? super List<UserFollowerBean>> subscriber) {
                try {
                    Response<List<UserFollowerBean>> followers = call.execute();
                    subscriber.onNext(followers.body());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });

        myObserve
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<UserFollowerBean>, List<UserFollowerBean>>() {
                    @Override
                    public List<UserFollowerBean> call(List<UserFollowerBean> userFollowerBeen) {
                        for (UserFollowerBean bean : userFollowerBeen) {
                            String name = "";
                            name = bean.getLogin().substring(0, 1).toUpperCase() + bean.getLogin().substring(1, bean.getLogin().length());
                            bean.setLogin(name);
                        }
                        return userFollowerBeen;
                    }
                })
                .map(new Func1<List<UserFollowerBean>, List<UserFollowerBean>>() {
                    @Override
                    public List<UserFollowerBean> call(List<UserFollowerBean> userFollowerBean) {
                        Collections.sort(userFollowerBean, new Comparator<UserFollowerBean>() {
                            @Override
                            public int compare(UserFollowerBean o1, UserFollowerBean o2) {
                                return o1.getLogin().compareTo(o2.getLogin());
                            }
                        });
                        return userFollowerBean;
                    }
                })
                .take(5)
                .subscribe(new Subscriber<List<UserFollowerBean>>() {
                    @Override
                    public void onCompleted() {
                        loading.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.dismiss();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<UserFollowerBean> userFollowerBeen) {
                        setFollowersInfo(userFollowerBeen);
                    }
                });

    }

    private void setUserInfo(GithubUserBean user) {
        if (user != null) {
            viewShell.removeAllViews();
            View view = LayoutInflater.from(mContext).inflate(R.layout.user_item_layout, null);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView id = (TextView) view.findViewById(R.id.userId);
            TextView creteaTime = (TextView) view.findViewById(R.id.createTime);
            TextView updateTime = (TextView) view.findViewById(R.id.updateTime);
            TextView bio = (TextView) view.findViewById(R.id.bio);
            ImageView avatar = (ImageView) view.findViewById(R.id.avatar);


            title.setText("Name: " + user.getLogin());
            bio.setText("Bio: " + user.getBio());
            id.setText("Id: " + String.valueOf(user.getId()));
            creteaTime.setText("createTime: " + user.getCreated_at());
            updateTime.setText("updateTime: " + user.getUpdated_at());
            Glide.with(mContext).load(user.getAvatar_url()).into(avatar);

            viewShell.addView(view);
        } else {
            Toast.makeText(mContext, "result is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void setFollowersInfo(List<UserFollowerBean> followers) {
        if (followers != null && followers.size() > 0) {
            for (UserFollowerBean user : followers) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.user_item_layout, null);
                TextView title = (TextView) view.findViewById(R.id.title);
                TextView id = (TextView) view.findViewById(R.id.userId);
                TextView creteaTime = (TextView) view.findViewById(R.id.createTime);
                TextView updateTime = (TextView) view.findViewById(R.id.updateTime);
                ImageView avatar = (ImageView) view.findViewById(R.id.avatar);

                title.setText("Name: " + user.getLogin());
                id.setText("Id: " + String.valueOf(user.getId()));
                creteaTime.setText("");
                updateTime.setText("");
                Glide.with(mContext).load(user.getAvatar_url()).into(avatar);

                viewShell.addView(view);
            }
        } else {
            Toast.makeText(mContext, "result is null", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        viewShell.removeAllViews();
        loading.show();
        name = username.getText().toString();
        if (TextUtils.isEmpty(name)) {
            name = "mike";
        }
        int i = v.getId();
        if (i == R.id.get) {
            SimpleRetrofit();
        } else if (i == R.id.get1) {
            LazyRetrofit();
        } else if (i == R.id.get2) {
            EasyRetrofit();
        } else if (i == R.id.get3) {
            RxRetrofit();
        } else if (i == R.id.get4) {
            RxRetrofitList();
        }
    }

}
