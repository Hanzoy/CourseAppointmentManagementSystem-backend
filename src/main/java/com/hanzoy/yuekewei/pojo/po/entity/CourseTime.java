package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

/**
 * 数据库course_time表实体类
 */
@Data
public class CourseTime {
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
     * 课时数量
     */
    private Integer count;
}
