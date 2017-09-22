package syway.txxs.com.syway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import syway.txxs.com.syway.po.User;
import syway.txxs.com.syway.util.ValidateUtil;

/**
 * Created by jianghuimin on 2017/9/21.
 * 注册
 */
public class RegisterActivity extends Activity{

    @BindView(R.id.regi_edt_phone)
    EditText regiEdtPphone;//注册的手机号

    @BindView(R.id.regi_edt_vcode)
    EditText regiEdtVcode;//注册验证码

    @BindView(R.id.regi_edt_password)
    EditText regiEdtPpassword;//注册密码

    @BindView(R.id.regi_btn_vcode)
    Button regiBtnVcode;//获取验证码按钮

    @BindView(R.id.regi_btn_register)
    Button regiBtnRegister;//注册按钮

    @BindView(R.id.regi_btn_logined)
    Button regiBtnLogin;//跳转登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        sendVcodePhone();//发送验证码
        regiToLogin();//注册跳转到登录页面
    }

    /**
     * 发送验证码
     * 1、验证手机号码
     * 2、判断是否已经注册
     * 3、发送验证码
     */
    public void sendVcodePhone(){
        String phoneNumber = regiEdtPphone.getText().toString();
        if(!ValidateUtil.isPhoneNumber(phoneNumber)){
            //不是手机号的情况下要异常处理
        }

        User user = new User();
        if(user!=null){
            //提示已经注册
        }else{
            //发送短信验证码
        }
    }

    /**
     * 注册页面向登录页面跳转
     */
    public void regiToLogin(){
        regiBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regiToLoginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(regiToLoginIntent);
            }
        });
    }

}
