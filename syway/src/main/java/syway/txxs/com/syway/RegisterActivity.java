package syway.txxs.com.syway;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import syway.txxs.com.syway.po.User;
import syway.txxs.com.syway.util.Constants;
import syway.txxs.com.syway.util.CountDownButtonUtil;
import syway.txxs.com.syway.util.ToastUtil;
import syway.txxs.com.syway.util.ValidateUtil;

/**
 * Created by jianghuimin on 2017/9/21.
 * 注册
 */
public class RegisterActivity extends Activity{

    OkHttpClient client = new OkHttpClient();

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
        regi();//注册
        regiToLogin();//注册跳转到登录页面
    }

    /**
     * 发送验证码
     * 1、验证手机号码
     * 2、判断是否已经注册
     * 3、发送验证码
     */
    public void sendVcodePhone(){
        regiBtnVcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = regiEdtPphone.getText().toString();
                if(!ValidateUtil.isPhoneNumber(phoneNumber)){
                    ToastUtil.setToastProperties(RegisterActivity.this, Constants.PHONE_NUMBER_WRONG_TIPS);
                    return;
                }
                User user = new User();
                if(user==null){
                    new AlertDialog.Builder(RegisterActivity.this).setTitle(Constants.PHONE_ALREADY_REGISTERED)
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“确认”后的操作
                                    Intent regiToLoginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                                    String phoneNumber = regiEdtPphone.getText().toString();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("phoneNumber", phoneNumber);
                                    regiToLoginIntent.putExtras(bundle);
                                    startActivity(regiToLoginIntent);
                                }
                            })
                            .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }else{
                    //发送短信验证码,具体发送在后端进行，andriod端控制发送的时间
                     //new CountDownButtonUtil(60000,1000,regiBtnVcode).start();//参数依次为总时长，计时时间间隔
                    getRequest();
                }
            }
        });
    }

    /**
     * 注册
     * 1、验证收到的验证码的正确性（后台进行）
     * 2、数据库注册（后台进行）
     */
    public void regi(){
        String vcode = regiEdtVcode.getText().toString();
        Boolean valid = true;//后台程序
        //验证码有效，执行注册操作
        if(valid){

        }else{
            ToastUtil.setToastProperties(RegisterActivity.this, Constants.PHONE_VCODE_WRONG_TIPS);
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
                Bundle bundle=new Bundle();
                regiToLoginIntent.putExtras(bundle);
                startActivity(regiToLoginIntent);
            }
        });
    }

    public  void getRequest() {
        final Request request=new Request.Builder()
                .get()
                .tag("User")
                .url("http://120.77.222.5:8080/user")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("WY","打印GET响应的数据：" + response.body().string());
                        ToastUtil.setToastProperties(RegisterActivity.this, "成功");
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
