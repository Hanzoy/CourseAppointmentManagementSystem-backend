package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.CourseAndUserInfo;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetAllCoursesResult {
    private ArrayList<CourseAndUserInfo> courseAndUserInfos;
}
