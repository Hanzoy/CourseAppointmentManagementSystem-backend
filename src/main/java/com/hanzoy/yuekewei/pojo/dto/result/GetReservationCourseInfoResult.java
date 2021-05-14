package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.bo.CourseTimetable;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetReservationCourseInfoResult {
    private ArrayList<CourseTimetable> courseTimetables;
}
