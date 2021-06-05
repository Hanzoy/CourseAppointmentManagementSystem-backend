package com.hanzoy.yuekewei.pojo.dto.param;

import lombok.Data;

@Data
public class GetTimetableByYearAndMonthParam {
    private String token;
    private String year;
    private String month;
}
