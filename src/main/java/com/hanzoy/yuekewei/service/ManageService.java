package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.bo.AdminTokenInfo;
import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
import com.hanzoy.yuekewei.pojo.po.entity.Admin;

public interface ManageService {

    GetAllCoursesResult getAllCourses(GetAllCoursesParam param);

    ManageLoginResult login(ManageLoginParam param);

    String writeAdminToToken(Admin admin);

    AdminTokenInfo getAdminTokenInfo(String token);

    GetAllUsersResult getAllUsers(GetAllUsersParam param);

    ChangeUserInformationResult changeUserInformation(ChangeUserInformationParam param);

    GetAllUsersByKeyResult getAllUsersByKey(GetAllUsersByKeyParam param);

    GetUserInfoResult getUserInfo(GetUserInfoParam param);
}
