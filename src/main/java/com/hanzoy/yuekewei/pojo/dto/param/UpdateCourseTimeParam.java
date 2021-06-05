package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCourseTimeParam {
    @NotEmpty(message = "不能为空")
    private String token;
    @NotEmpty(message = "不能为空")
    private String openid;
    @NotNull(message = "不能为空")
    private Integer courseId;
    @NotNull(message = "不能为空")
    private Double count;
}
