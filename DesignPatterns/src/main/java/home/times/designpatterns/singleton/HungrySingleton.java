package home.times.designpatterns.singleton;

/**
 * Created by rookie on 2017-04-05.
 */

public class HungrySingleton {

    private static HungrySingleton mInstance = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return mInstance;
    }
}
