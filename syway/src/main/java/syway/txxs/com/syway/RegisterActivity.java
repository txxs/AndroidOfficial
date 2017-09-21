package syway.txxs.com.syway;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jianghuimin on 2017/9/21.
 * 注册
 */
public class RegisterActivity extends Activity{

    private Button btn_vcode;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //initToolBar(toolbar,"账号注册",true);

        // 获取验证码--start
/*        btn_vcode = (Button) findViewById(R.id.btn_vcode);
        btn_vcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownButtonHelper helper = new CountDownButtonHelper(btn_yzm,"倒计时",60,1);

                helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {
                    @Override
                    public void finish() {
                        // Toast.makeText(RegisterActivity.this,"倒计时结束",Toast.LENGTH_SHORT).show();
                        btn_vcode.setText("再次获取");
                    }
                });
                helper.start();
            }
        }); // 获取验证码--end*/
    }
}
