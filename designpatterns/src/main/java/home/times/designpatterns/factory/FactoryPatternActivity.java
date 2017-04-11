package home.times.designpatterns.factory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import home.times.designpatterns.R;
import home.times.designpatterns.V;

public class FactoryPatternActivity extends AppCompatActivity {
    private TextView bike_result;
    private BicycleFactory factory;
    private Bicycle mBicycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_pattern);
        bike_result = V.f(this, R.id.bike_result);
    }


    public void MobikeClick(View view) {
        factory = new MobikeFactory();
        mBicycle = factory.makeBicycle();
        updateView(mBicycle);
    }

    public void OfoClick(View view) {
        factory = new OfoFactory();
        mBicycle = factory.makeBicycle();
        updateView(mBicycle);
    }
    private void updateView(Bicycle mBicycle) {
        bike_result.setText("");
        StringBuilder sb = new StringBuilder();
        sb.append(mBicycle.manufacture())
                .append("\n")
                .append(mBicycle.billing());
        bike_result.setText(sb.toString());
    }
}
