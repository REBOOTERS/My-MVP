package huyifei.mymvp.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        String time = intent.getStringExtra("date");
        Toast.makeText(context, "Received time is " + time, Toast.LENGTH_SHORT).show();

        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
