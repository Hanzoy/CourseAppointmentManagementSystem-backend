package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.CourseAndTimetableInfo;
import lombok.Data;

import java.util.ArrayList;

@Data
public class HasReservationCourseResult {
    ArrayList<CourseAndTimetableInfo> courseInfos;
}
