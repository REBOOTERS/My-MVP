package home.times.designpatterns.singleton;

/**
 * Created by rookie on 2017-04-05.
 */

public class LazySingleton {
    private static LazySingleton mInstance = null;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if (mInstance == null) {
            synchronized (LazySingleton.class) {
                if (mInstance == null) {
                    mInstance = new LazySingleton();
                }
            }
        }

        return mInstance;
    }
}
