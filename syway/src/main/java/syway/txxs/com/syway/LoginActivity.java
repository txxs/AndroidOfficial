package syway.txxs.com.syway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jianghuimin on 2017/9/21.
 */

public class LoginActivity extends Activity {

    @BindView(R.id.login_edt_phone)
    EditText loginEdtPhone;//注册的手机号

    @BindView(R.id.login_btn_logined_forget)
    Button loginBtnLoginedForget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        String phoneNumber = bundle.getString("phoneNumber");
        if(phoneNumber!=null){
            loginEdtPhone.setText(phoneNumber);
        }
        loginToForgt();
    }

    public void loginToForgt(){
        loginBtnLoginedForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ForgetActivity.class);
                startActivity(intent);
            }
        });
    }
}

