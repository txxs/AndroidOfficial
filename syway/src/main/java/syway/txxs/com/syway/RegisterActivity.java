package syway.txxs.com.syway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    //@BindView(R.id.regi_btn_login)
    private Button regiBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       // ButterKnife.bind(this);
        regiBtnLogin=(Button) findViewById(R.id.regi_btn_logined);
        regiBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regiToLoginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(regiToLoginIntent);
            }
        });
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

    /**
     * 注册页面向登录页面跳转
     */
    public void regiToLogin(){

    }

}
