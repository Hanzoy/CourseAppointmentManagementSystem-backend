package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

/**
 * 数据库course表实体类
 */
@Data
public class Course {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 课程名
     */
    private String name;

    /**
     * 课程背景图片
     */
    private Integer backgroundUrl;

    private Double money;
}
