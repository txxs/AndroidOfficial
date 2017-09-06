package syway.txxs.com.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jianghuimin on 2017/9/6.
 */

public class AnotherBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("bdstore")+"AnotherBroadcastReceiver recivered",Toast.LENGTH_LONG).show();
    }
}
