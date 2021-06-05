package com.hanzoy.yuekewei.pojo.po;

import lombok.Data;

@Data
public class TimetableExampleInfo {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 星期
     */
    private Integer week;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 人数上线
     */
    private Integer toplimit;

    /**
     * 备注
     */
    private String remark;

    /**
     * 地址
     */
    private String address;

    private Integer coachId;
    private Double cost;
}
