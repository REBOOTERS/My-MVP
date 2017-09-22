package home.times.designpatterns.factory;

/**
 * Created by engineer on 2017/4/10.
 */

public class Ofo implements Bicycle {
    @Override
    public String manufacture() {
        System.out.print("ofo单车---黄色");
        return "ofo单车---黄色";
    }

    @Override
    public String billing() {
        System.out.print("ofo单车: 师生认证用户0.5元/小时，非师生用户1元/小时");
        return "ofo单车: 师生认证用户0.5元/小时，非师生用户1元/小时";
    }
}
