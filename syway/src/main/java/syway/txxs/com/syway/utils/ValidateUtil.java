package syway.txxs.com.syway.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jianghuimin on 2017/9/22.
 */

public class ValidateUtil {

    /**
     * 验证手机号码，中国大陆
     * @param phoneNumber
     * @return
     */
    public static Boolean isPhoneNumber(String phoneNumber){
        String phoneNumberRule = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern pattern = Pattern.compile(phoneNumberRule);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     *对字符串进行非空判断
     * @param
     * @return
     */
    public static Boolean notNull(String str){
        if(str==null||str.trim().equals("")){
            return false;
        }
        return true;
    }
}
