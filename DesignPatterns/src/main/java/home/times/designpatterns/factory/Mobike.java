package home.times.designpatterns.factory;

/**
 * Created by engineer on 2017/4/10.
 */

public class Mobike implements Bicycle {
    @Override
    public String manufacture() {
        System.out.print("摩拜单车---橙色");
        return "摩拜单车---橙色";
    }

    @Override
    public String billing() {
        System.out.print("Mobike每30分钟收费1元");
        return "Mobike每30分钟收费1元";
    }
}
