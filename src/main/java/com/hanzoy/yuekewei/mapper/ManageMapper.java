package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.CourseAndUserInfo;
import com.hanzoy.yuekewei.pojo.po.UserCourseTimeInfo;
import com.hanzoy.yuekewei.pojo.po.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface ManageMapper {
    Admin getAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    ArrayList<CourseAndUserInfo> getAllCourseInfo();

    ArrayList<UserCourseTimeInfo> getUserCourseInfoByCourseId(@Param("courseId") Integer courseId);
}
