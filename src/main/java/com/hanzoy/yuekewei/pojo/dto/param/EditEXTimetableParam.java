package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EditEXTimetableParam {
    @NotEmpty(message = "不能为空")
    private String token;
    @NotNull(message = "不能为空")
    private Integer id;
    @NotNull(message = "不能为空")
    private Integer courseId;
    @NotNull(message = "不能为空")
    private Integer week;
    @NotEmpty(message = "不能为空")
    private String startTime;
    @NotEmpty(message = "不能为空")
    private String endTime;
    @NotEmpty(message = "不能为空")
    private String remark;
    @NotNull(message = "不能为空")
    private Integer toplimit;
    @NotEmpty(message = "不能为空")
    private String address;
    @NotNull(message = "不能为空")
    private Integer coachId;
    @NotNull(message = "不能为空")
    private Double cost;
}