package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

/**
 * 数据库timetable_coach_merge表实体类
 */
@Data
public class TimetableCoachMerge {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 课程安排id
     */
    private Integer timetableId;

    /**
     * 教练openid
     */
    private String coachId;
}
