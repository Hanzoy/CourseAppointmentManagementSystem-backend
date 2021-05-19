package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReservationCourseParam {

    @NotEmpty(message = "不能为空")
    private String token;

    /**
     * timetableId
     */
    @NotNull
    private Integer id;

    @NotNull
    private Boolean isReservation;
}
