package home.times.designpatterns.singleton;

/**
 * Created by engineer on 2017/4/5.
 */

public class StaticSingleton {
    private StaticSingleton(){

    }

    public static StaticSingleton getInstance(){
        return SingletonHolder.mInstance;
    }


    /**
     * 静态内部类
     */
    private static class SingletonHolder{
        private static final StaticSingleton mInstance=new StaticSingleton();
    }

}
