package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class GetReservationCourseInfoParam {

    @NotEmpty(message = "不能为空")
    private String token;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}", message = "格式错误")
    private String Date;
}
