package com.hanzoy.yuekewei.pojo.dto.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginResult {
    /**
     * 是否需要注册
     */
    private Boolean needRegister;

    /**
     * 用户token
     */
    private String token;
}
