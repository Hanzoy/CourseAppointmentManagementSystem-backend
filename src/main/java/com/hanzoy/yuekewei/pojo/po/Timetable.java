package com.hanzoy.yuekewei.pojo.po;

import lombok.Data;

@Data
public class Timetable {
    /**
     * timetable Id
     */
    private Integer id;

    /**
     * 教练姓名
     */
    private String coachName;

    /**
     * 教练头像地址
     */
    private String coachAvatarUrl;

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
     * 当前预约的人数
     */
    private Integer count;

    /**
     * 是否预约
     */
    private Boolean isReservation;
}
