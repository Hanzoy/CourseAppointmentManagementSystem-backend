package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ChangeInformationParam {

    @NotEmpty(message = "不能为空")
    private String token;

    @NotEmpty(message = "不能为空")
    private String name;

    @NotEmpty(message = "不能为空")
    @Pattern(regexp = "^([1][3,4,5,6,7,8,9])\\d{9}$", message = "格式错误")
    private String phone;
}
