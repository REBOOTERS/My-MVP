package home.smart.fly.ex;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 06-23-2019
 */
public abstract class AbsSubject<T> implements InterfaceSubject<T> {

    @Override
    public void dingyue(GCZ<? extends T> gcz) {
        dingyueReal(gcz);
    }

    abstract void dingyueReal(GCZ<? extends T> gcz);

}
