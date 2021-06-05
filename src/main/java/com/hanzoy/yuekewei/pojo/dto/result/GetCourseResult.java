package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.CourseInfo;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetCourseResult {
    ArrayList<CourseInfo> course;
}
