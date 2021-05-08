package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterParam {
    /**
     * 用户登陆调用微信登陆接口的code
     */
    @NotEmpty(message = "不能为空")
    private String code;

    /**
     * 用户自己输入的name
     */
//    @NotEmpty(message = "不能为空")
    private String name;

    /**
     * 用户电话
     */
//    @Pattern(regexp = "^([1][3,4,5,6,7,8,9])\\d{9}$")
    private String phone;

    /**
     * 微信昵称
     */
    @NotEmpty(message = "不能为空")
    private String nickName;

    /**
     * 微信头像
     */
    @NotEmpty(message = "不能为空")
    private String avatarUrl;
}
