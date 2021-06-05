package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.TimetableInfo;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetTimetableByYearAndMonthResult {
    ArrayList<TimetableInfo> timetableInfos;
}
