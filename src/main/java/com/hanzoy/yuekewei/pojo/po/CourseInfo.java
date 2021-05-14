package com.hanzoy.yuekewei.pojo.po;

import lombok.Data;

@Data
public class CourseInfo {
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
    private String backgroundUrl;

    /**
     * 剩余课时数量
     */
    private Integer count;
}
