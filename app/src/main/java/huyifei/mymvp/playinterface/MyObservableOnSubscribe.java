package huyifei.mymvp.playinterface;


import io.reactivex.annotations.NonNull;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2017/12/13
 * desc   :
 * version: 1.0
 */
public interface MyObservableOnSubscribe<T> {
    void subscribe(@NonNull MyObservableEmitter<T> emitter);
}
