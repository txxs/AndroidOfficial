package syway.txxs.com.sharedpreferencesstorepwd;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by jianghuimin on 2017/9/6.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
