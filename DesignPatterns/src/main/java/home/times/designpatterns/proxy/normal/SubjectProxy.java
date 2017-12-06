package home.times.designpatterns.proxy.normal;

/**
 * @author : Rookie
 *         e-mail : yingkongshi11@gmail.com
 *         time   : 2017/12/06
 *         desc   :
 *         version: 1.0
 */

public class SubjectProxy implements Subject {
    Subject mSubject;

    public SubjectProxy(Subject subject) {
        mSubject = subject;
    }

    @Override
    public void play() {
        mSubject.play();
    }

    @Override
    public void fly() {
        mSubject.fly();
    }
}
