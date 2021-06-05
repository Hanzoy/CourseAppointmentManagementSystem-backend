package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GetTimetableByDateAndCourseIdParam {
    @NotEmpty(message = "不能为空")
    private String token;
    @NotEmpty(message = "不能为空")
    private String date;
    @NotNull(message = "不能为空")
    private Integer courseId;
}
