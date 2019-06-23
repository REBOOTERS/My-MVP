package home.smart.fly.ex;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 06-23-2019
 */
public interface GCZ<T> {

    void onSuccess(T t);

    void onFail(Exception e);
}
