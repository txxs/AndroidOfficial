package syway.txxs.com.syway.util;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by jianghuimin on 2017/9/23.
 */

public class CountDownButtonUtil extends CountDownTimer {

    private Button button;

    public CountDownButtonUtil(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.button=button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setTextSize(14);
        button.setText(millisUntilFinished / 1000 + "秒后重新发送");
        button.setClickable(false);
    }

    @Override
    public void onFinish() {
        button.setText("获取验证码");
        button.setClickable(true);
    }
}
