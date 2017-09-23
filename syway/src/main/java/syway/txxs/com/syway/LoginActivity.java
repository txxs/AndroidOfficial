package syway.txxs.com.syway;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jianghuimin on 2017/9/21.
 */

public class LoginActivity extends Activity {

    @BindView(R.id.login_edt_phone)
    EditText loginEdtPhone;//注册的手机号

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
    }
}
