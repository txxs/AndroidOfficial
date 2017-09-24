package syway.txxs.com.syway;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import syway.txxs.com.syway.util.Constants;
import syway.txxs.com.syway.util.CountDownButtonUtil;
import syway.txxs.com.syway.util.ToastUtil;
import syway.txxs.com.syway.util.ValidateUtil;

/**
 * Created by jianghuimin on 2017/9/24.
 */

public class ForgetActivity extends Activity {

    @BindView(R.id.forget_btn_vcode)
    Button forgetBtnVcode;

    @BindView(R.id.forget_edt_phone)
    EditText forgetEdtPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        sendVcode();
    }

    /**
     * 发送验证码
     * 1、验证手机号码
     * 2、判断是否已经注册
     * 3、发送验证码
     */
    public void sendVcode(){
        forgetBtnVcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = forgetEdtPhone.getText().toString();
                if(!ValidateUtil.isPhoneNumber(phoneNumber)){
                    ToastUtil.setToastProperties(ForgetActivity.this, Constants.PHONE_NUMBER_WRONG_TIPS);
                    return;
                }
                //发送短信验证码,具体发送在后端进行，andriod端控制发送的时间
                new CountDownButtonUtil(60000,1000,forgetBtnVcode).start();//参数依次为总时长，计时时间间隔
            }
        });
    }
}
