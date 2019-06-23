package home.smart.fly.ex;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 06-23-2019
 */
public class Driver {

    public void main() {
        ConSubject<String> conSubject = new ConSubject<>(new Bridge<String>() {
            @Override
            public void subcribe(Emiter<String> emiter) {
                emiter.onSuccess("eeee");
                emiter.onFail(new Exception("2222"));
            }
        });

        conSubject.dingyueReal(new GCZ<String>() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }
}
