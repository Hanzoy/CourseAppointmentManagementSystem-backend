package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

/**
 * 数据库timetable表实体类
 */
@Data
public class Timetable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 日期
     */
    private String date;

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

    /**
     *
     */
    private Double cost;

}
