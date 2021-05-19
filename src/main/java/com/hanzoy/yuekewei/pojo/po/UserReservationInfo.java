package com.hanzoy.yuekewei.pojo.po;

import lombok.Data;

@Data
public class UserReservationInfo {
    /**
     * timetable Id
     */
    private Integer id;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 当前剩余预约人数
     */
    private Integer count;

    /**
     * 是否预约
     */
    private Boolean isReservation;
}
