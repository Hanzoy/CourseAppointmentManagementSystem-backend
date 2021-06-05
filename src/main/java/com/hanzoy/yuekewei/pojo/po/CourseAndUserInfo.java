package com.hanzoy.yuekewei.pojo.po;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CourseAndUserInfo {
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

    private Double money;
    /**
     * 每个课程的用户信息
     */
    private ArrayList<UserCourseTimeInfo> userCourseTimeInfo;
}
