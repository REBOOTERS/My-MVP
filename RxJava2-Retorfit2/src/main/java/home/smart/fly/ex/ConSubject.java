package home.smart.fly.ex;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 06-23-2019
 */
public class ConSubject<T> extends AbsSubject<T> {

    private Bridge mBridge;

    public ConSubject(Bridge<T> bridge) {
        mBridge = bridge;
    }


    @Override
    void dingyueReal(GCZ<? extends T> gcz) {
        Emiter<T> emiter = new MyEmiter<>(gcz);
        mBridge.subcribe(emiter);
    }

    static class MyEmiter<T> implements Emiter<T> {

        GCZ mGCZ;

        public MyEmiter(GCZ GCZ) {
            mGCZ = GCZ;
        }

        @Override
        public void onSuccess(T t) {
            mGCZ.onSuccess(t);
        }

        @Override
        public void onFail(Exception e) {
            mGCZ.onFail(e);
        }
    }
}
