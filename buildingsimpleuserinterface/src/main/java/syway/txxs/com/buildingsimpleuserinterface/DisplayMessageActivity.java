package syway.txxs.com.buildingsimpleuserinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static syway.txxs.com.buildingsimpleuserinterface.util.Constant.EXTRA_MESSAGE;

/**
 * Created by jianghuimin on 2017/9/4.
 */

public class DisplayMessageActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_message);
        Intent intent = getIntent();
        String msg = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(msg);
        LinearLayout layout = (LinearLayout) findViewById(R.id.content);
        layout.addView(textView);
    }

}
