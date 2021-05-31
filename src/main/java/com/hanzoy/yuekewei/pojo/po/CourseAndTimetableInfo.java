package com.hanzoy.yuekewei.pojo.po;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CourseAndTimetableInfo {
    private Integer id;
    private String courseName;
    private ArrayList<TimetableInfo> timetableInfos;
}
