package home.times.designpatterns.factory;

/**
 * Created by engineer on 2017/4/10.
 */

/**
 * 抽象单车产品
 */

public interface Bicycle {
    /**
     * 单车生产方式
     */
    void manufacture();

    /**
     * 单车计费方式
     */
    void billing();
}
