package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.dto.param.UserLoginParam;
import com.hanzoy.yuekewei.pojo.dto.param.UserRegisterParam;
import com.hanzoy.yuekewei.pojo.dto.result.UserLoginResult;
import com.hanzoy.yuekewei.pojo.dto.result.UserRegisterResult;
import com.hanzoy.yuekewei.pojo.po.entity.Users;

public interface UserService {
    UserLoginResult userLogin(UserLoginParam code);

    String writeUsersToToken(Users users);

    UserRegisterResult userRegister(UserRegisterParam param);
}
