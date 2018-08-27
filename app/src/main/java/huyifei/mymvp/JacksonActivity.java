package huyifei.mymvp;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import java8.util.Optional;
import java8.util.function.Consumer;

public class JacksonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jackson);
        String ssid=getWifiSSID();
        TextView wifiText= findViewById(R.id.wifissid);
        wifiText.setText("wifi SSID：" + ssid);
    }


    @Nullable
    private String getWifiSSID() {

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


        if (wifiManager != null && wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                    return wifiInfo.getSSID();
                }
            }
        }

//        Optional.ofNullable(wifiManager).ifPresent((WifiManager wifiManager1) -> {
//            if (wifiManager1.isWifiEnabled()) {
//                WifiInfo wifiInfo = wifiManager1.getConnectionInfo();
//                Optional.ofNullable(wifiInfo).ifPresent((Consumer<WifiInfo>) (WifiInfo wifiInfo1) -> {
//                    NetworkInfo.DetailedState detailedState = WifiInfo.getDetailedStateOf(wifiInfo1.getSupplicantState());
//                    if (detailedState == NetworkInfo.DetailedState.CONNECTED || detailedState == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
//
//                        return wifiInfo1.getSSID();
//                    }
//                });
//            }
//
//
//        });
        return null;
    }


}
