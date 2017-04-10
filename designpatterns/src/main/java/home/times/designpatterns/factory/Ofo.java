package home.times.designpatterns.factory;

/**
 * Created by engineer on 2017/4/10.
 */

public class Ofo implements Bicycle {
    @Override
    public void manufacture() {
        System.out.print("摩拜单车---橙色");
    }

    @Override
    public void billing() {
        System.out.print("Mobike每30分钟收费1元");
    }
}
