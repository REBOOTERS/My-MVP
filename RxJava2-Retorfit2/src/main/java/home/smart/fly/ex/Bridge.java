package home.smart.fly.ex;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 06-23-2019
 */
public interface Bridge<T> {

    void subcribe(Emiter<T> emiter);
}
