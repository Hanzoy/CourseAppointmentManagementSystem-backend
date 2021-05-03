package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

import java.util.Date;

/**
 * 数据库timetable_users_merge表实体类
 */
@Data
public class TimetableUsersMerge {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 课程安排id
     */
    private Integer timetableId;

    /**
     * 用户openid
     */
    private String userId;

    /**
     * 时间戳
     */
    private Date time;
}
