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

/**
 * @author: zhuyongging
 * @since: 2019-04-10   直接完成生命周期的绑定和线程的切换
 */
public class SimpleRequestTransformer<T> implements ObservableTransformer<T, T>, CompletableTransformer {


    private LifecycleTransformer<T> mLifecycleTransformer;

    public SimpleRequestTransformer() {
        this(null);
    }

    public SimpleRequestTransformer(LifecycleTransformer<T> lifecycleTransformer) {
        mLifecycleTransformer = lifecycleTransformer;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        if (mLifecycleTransformer != null) {
            upstream = upstream.compose(mLifecycleTransformer);
        }

        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        if (mLifecycleTransformer != null) {
            upstream = upstream.compose(mLifecycleTransformer);
        }
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
