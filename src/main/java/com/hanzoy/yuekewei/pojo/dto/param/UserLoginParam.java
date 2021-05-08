package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginParam {
    /**
     * 用户登陆调用微信登陆接口的code
     */
    @NotEmpty(message = "不能为空")
    private String code;
}
