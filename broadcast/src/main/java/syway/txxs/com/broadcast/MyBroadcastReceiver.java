package syway.txxs.com.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jianghuimin on 2017/9/6.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("bdstore")+"received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
    }
}
