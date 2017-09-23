package syway.txxs.com.syway.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jianghuimin on 2017/9/23.
 */

public class ToastUtil {

    /**
     * 只接受一个参数toast
     * @param toast
     */
    public static void setToastProperties(Toast toast){
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void setToastProperties(Activity activity, String tips){
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(syway.txxs.com.syway.R.layout.view_toast_custom,
                (ViewGroup) activity.findViewById(syway.txxs.com.syway.R.id.lly_toast));
        TextView tv_msg = (TextView) view.findViewById(syway.txxs.com.syway.R.id.tv_msg);
        tv_msg.setText(tips);
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}
