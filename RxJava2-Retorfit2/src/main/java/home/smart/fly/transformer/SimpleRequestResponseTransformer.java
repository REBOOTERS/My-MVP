package home.smart.fly.transformer;

import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * @author: zhuyongging
 * @since: 2019-04-10   直接完成生命周期的绑定和线程的切换 ，和 Response body 的剥离
 */
public abstract class SimpleRequestResponseTransformer<T> implements ObservableTransformer<Response<T>, T>, CompletableTransformer {


    private LifecycleTransformer<Response<T>> mLifecycleTransformer;

    public SimpleRequestResponseTransformer() {
        this(null);
    }

    public SimpleRequestResponseTransformer(LifecycleTransformer<Response<T>> lifecycleTransformer) {
        mLifecycleTransformer = lifecycleTransformer;
    }

    @Override
    public ObservableSource<T> apply(Observable<Response<T>> upstream) {
        if (mLifecycleTransformer != null) {
            upstream = upstream.compose(mLifecycleTransformer);
        }

        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(response -> {
                    if (response.isSuccessful()) {
                        T t = response.body();
                        if (t != null) {
                            return Observable.just(t);
                        } else {
                            return Observable.error(new Throwable("response body = null"));
                        }
                    } else {
                        return Observable.error(new Throwable("network failed"));
                    }
                })
                .onErrorResumeNext(throwable -> {
                    onRequestFailure(throwable);
                    return Observable.empty();
                })
                .doOnNext(this::onRequestSuccess)
                .doFinally(() -> System.out.print("all done"));
    }

    abstract public void onRequestFailure(Throwable throwable);

    abstract public void onRequestSuccess(T result);

    @Override
    public CompletableSource apply(Completable upstream) {
        if (mLifecycleTransformer != null) {
            upstream = upstream.compose(mLifecycleTransformer);
        }
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
