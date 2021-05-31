package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EditSwiperParam {
    @NotEmpty(message = "不能为空")
    private String token;
    private Integer id;
    private String name;
    private String remark;
}
