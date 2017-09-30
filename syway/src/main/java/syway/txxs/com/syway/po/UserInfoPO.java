package syway.txxs.com.syway.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author:jianghuimin
 * @Date: 2017/8/17
 * @Time:20:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class UserInfoPO {
    //物理主键
    private int id;
    //用户的登录标识
    private String token;
    //业务主键
    private String userNum;
    //注册或者登录标识
    private String regOrLog;
    //电话号码
    private String phoneNum;
    //密码
    private String password;
    //用户名
    private String userName;
    //性别
    private String sex;
    //头像存储的URL
    private String headUrl;
    //身份证号码
    private String idcardNum;
    //微信账号
    private String wechat;
    //支付宝账号
    private String alipay;
    //用户创建的时间
    private Date createTime;
    //用户最近更新的时间
    private Date updateTime;
}
