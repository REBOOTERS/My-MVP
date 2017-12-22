package huyifei.mymvp.playinterface;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2017/12/13
 * desc   :
 * version: 1.0
 */
public class Test {
    MyObservableOnSubscribe mMyObservableOnSubscribe=new MyObservableOnSubscribe() {
        @Override
        public void subscribe(MyObservableEmitter emitter) {
            emitter.onNext("11");
            emitter.onComplete();
        }
    };
}
