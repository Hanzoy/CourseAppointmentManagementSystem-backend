package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.TimetableInfo;
import com.hanzoy.yuekewei.pojo.po.TimetableInfos;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetTimetableByDateAndCourseIdResult {
    ArrayList<TimetableInfos> timetableInfos;
}
