package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.CourseInfo;
import com.hanzoy.yuekewei.pojo.po.entity.Course;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetMyCourseInfoResult {
    ArrayList<CourseInfo> courseInfo;
}
