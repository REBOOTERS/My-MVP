package home.times.designpatterns.proxy.normal;

/**
 * author : Rookie
 * e-mail : yingkongshi11@gmail.com
 * time   : 2017/12/06
 * desc   :
 * version: 1.0
 */

public class ConcreteSubject implements Subject {
    @Override
    public void play() {
        System.out.println("I'm Play");
    }

    @Override
    public void fly() {
        System.out.println("I'm fly");
    }
}
