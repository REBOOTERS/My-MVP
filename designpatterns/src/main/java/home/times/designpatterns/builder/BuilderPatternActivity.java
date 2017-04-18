package home.times.designpatterns.builder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import home.times.designpatterns.R;
import home.times.designpatterns.V;

public class BuilderPatternActivity extends AppCompatActivity {
    private TextView bike_result;
    private Bicycle mBicycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder_pattern);
        bike_result = V.f(this, R.id.bike_result);
    }

    /**
     * 普通自行车
     * @param view
     */
    public void NormalBike(View view) {
        Bicycle.BicycleBuilder builder=new Bicycle.BicycleBuilder();
        mBicycle=builder.build();
        updateView(mBicycle);
    }
    /**
     * 膜拜单车
     * @param view
     */
    public void Mobike(View view) {
        Bicycle.BicycleBuilder builder=new Bicycle.BicycleBuilder()
                .setColor("橙色")
                .setName("膜拜单车")
                .setCharge(1.0)
                .setNumber(10010)
                .setType(Bicycle.SHARED);
        mBicycle=builder.build();
        updateView(mBicycle);
    }
    /**
     * OFO 单车
     * @param view
     */
    public void Ofo(View view) {
        Bicycle.BicycleBuilder builder=new Bicycle.BicycleBuilder()
                .setColor("黄色")
                .setName("OFO单车")
                .setCharge(0.5)
                .setNumber(40010)
                .setType(Bicycle.SHARED);
        mBicycle=builder.build();
        updateView(mBicycle);
    }


    private void updateView(Bicycle mBicycle) {
        bike_result.setText("");
        bike_result.setText(mBicycle.toString());
    }
}
