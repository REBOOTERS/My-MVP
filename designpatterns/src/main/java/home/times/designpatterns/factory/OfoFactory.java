package home.times.designpatterns.factory;

/**
 * Created by engineer on 2017/4/10.
 */

public class OfoFactory implements BicycleFactory {
    @Override
    public Bicycle makeBicycle() {
        return new Ofo();
    }
}
