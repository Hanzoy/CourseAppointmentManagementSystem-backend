package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ManageGetSwiperParam {
    @NotEmpty(message = "不能为空")
    private String token;
}
