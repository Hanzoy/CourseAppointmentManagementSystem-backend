package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

import java.util.Date;

/**
 * 数据库course_time_operation表实体类
 */
@Data
public class CourseTimeOperation {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户openid
     */
    private String userId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 操作
     */
    private Integer operation;

    /**
     * 时间戳
     */
    private Date time;

}
