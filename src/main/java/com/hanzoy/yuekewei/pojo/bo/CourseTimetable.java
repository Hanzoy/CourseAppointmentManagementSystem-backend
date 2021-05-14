package com.hanzoy.yuekewei.pojo.bo;

import com.hanzoy.yuekewei.pojo.po.Timetable;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CourseTimetable {
    private Integer id;
    private ArrayList<Timetable> timetables;
}
