package home.times.designpatterns.factory;

/**
 * Created by engineer on 2017/4/10.
 */

public class Client {
    public static void main(String[] args) {
        BicycleFactory factory=new MobikeFactory();
        Bicycle mBicycle=factory.makeBicycle();
        new Client().ride(mBicycle);

    }

    public void ride(Bicycle bicycle) {
        System.out.println("我在使用");
        bicycle.manufacture();
        bicycle.billing();
    }
}
