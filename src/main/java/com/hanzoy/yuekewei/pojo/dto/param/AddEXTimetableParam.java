package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddEXTimetableParam {
    @NotEmpty(message = "不能为空")
    private String token;
    private Integer courseId;
    private Integer week;
    private String startTime;
    private String endTime;
    private String remark;
    private Integer toplimit;
    private String address;
    private Integer coachId;
    private Double cost;
}
