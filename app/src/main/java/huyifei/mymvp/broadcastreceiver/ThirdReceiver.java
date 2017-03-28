package huyifei.mymvp.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ThirdReceiver extends BroadcastReceiver {
    public ThirdReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String msg = intent.getStringExtra("msg");
        Toast.makeText(context, "Received msg is " + msg, Toast.LENGTH_SHORT).show();
    }
}
