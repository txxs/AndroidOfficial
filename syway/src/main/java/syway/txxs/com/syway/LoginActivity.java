package syway.txxs.com.syway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Request;
import syway.txxs.com.syway.po.UserInfoPO;
import syway.txxs.com.syway.utils.Constants;
import syway.txxs.com.syway.utils.ToastUtil;
import syway.txxs.com.syway.utils.ValidateUtil;
import syway.txxs.com.syway.utils.httputil.OkHttpUtil;
import syway.txxs.com.syway.utils.httputil.ResultCallback;
import syway.txxs.com.syway.utils.httputil.UserCallback;

/**
 * Created by jianghuimin on 2017/9/21.
 */

public class LoginActivity extends Activity {

    @BindView(R.id.login_edt_phone)
    EditText loginEdtPhone;//注册的手机号

    @BindView(R.id.login_edt_password)
    EditText loginEdtPassword;//登录密码

    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;

    @BindView(R.id.login_btn_logined_forget)
    Button loginBtnLoginedForget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //已经注册的号码， 直接传递到登录页面
        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null&&bundle.getString("phoneNumber")!=null){
            loginEdtPhone.setText(bundle.getString("phoneNumber"));
        }
        login();
        loginToForgt();//忘记登录
    }

    public void login(){
        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneNumber = loginEdtPhone.getText().toString();
                //final String password =  loginEdtPassword.getText().toString();
                final String password =  "test";
                if(!ValidateUtil.isPhoneNumber(phoneNumber)){
                    ToastUtil.setToastProperties(LoginActivity.this, Constants.PHONE_NUMBER_WRONG_TIPS);
                    return;
                }
                if(!ValidateUtil.notNull(password)){
                    ToastUtil.setToastProperties(LoginActivity.this, Constants.PHONE_PASSWORD_NULL);
                    return;
                }
                String URL = "http://120.77.222.5:8080/user";
                OkHttpUtil.asynGet(URL, new ResultCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(Object response) {
                        ObjectMapper mapper = new ObjectMapper();
                        UserInfoPO userInfoPO = null;
                        try {
                            userInfoPO = mapper.readValue(response.toString(),UserInfoPO.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(userInfoPO!=null&&password.equals(userInfoPO.getPassword())){
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    /**
     * 忘记登录的跳转
     */
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

