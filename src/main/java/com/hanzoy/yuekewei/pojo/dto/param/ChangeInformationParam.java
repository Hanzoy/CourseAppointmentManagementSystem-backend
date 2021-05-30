package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ChangeInformationParam {

    @NotEmpty(message = "不能为空")
    private String token;

    @NotEmpty(message = "不能为空")
    private String name;

    @NotEmpty(message = "不能为空")
    private String phone;
}
