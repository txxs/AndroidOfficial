package syway.txxs.com.syway.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jianghuimin on 2017/9/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class User {
    private Integer id;//物理主键
    private String phoneNumber;//电话号码
    private String password;//密码
}
