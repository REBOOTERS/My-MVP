package home.times.designpatterns.proxy.normal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import home.times.designpatterns.R;

public class StaticProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_proxy);

        Subject mSubject = new ConcreteSubject();
        SubjectProxy mSubjectProxy = new SubjectProxy(mSubject);

        mSubjectProxy.play();
        mSubjectProxy.fly();
    }
}
