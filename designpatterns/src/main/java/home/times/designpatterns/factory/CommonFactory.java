package home.times.designpatterns.factory;

/**
 * Created by engineer on 2017/4/12.
 */


/**
 * 所有产品公用一个工厂
 */
public class CommonFactory {
    public static Bicycle makeBicycle(String type) {
        switch (type) {
            case "mobike":
                return new Mobike();
            case "ofo":
                return new Ofo();
            default:
                return null;

        }

    }
}
