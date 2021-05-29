package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.bo.AdminTokenInfo;
import com.hanzoy.yuekewei.pojo.dto.param.GetAllCoursesParam;
import com.hanzoy.yuekewei.pojo.dto.param.GetAllUsersParam;
import com.hanzoy.yuekewei.pojo.dto.param.ManageLoginParam;
import com.hanzoy.yuekewei.pojo.dto.result.GetAllCoursesResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetAllUsersResult;
import com.hanzoy.yuekewei.pojo.dto.result.ManageLoginResult;
import com.hanzoy.yuekewei.pojo.po.entity.Admin;

public interface ManageService {

    GetAllCoursesResult getAllCourses(GetAllCoursesParam param);

    ManageLoginResult login(ManageLoginParam param);

    String writeAdminToToken(Admin admin);

    AdminTokenInfo getAdminTokenInfo(String token);

    GetAllUsersResult getAllUsers(GetAllUsersParam param);
}
