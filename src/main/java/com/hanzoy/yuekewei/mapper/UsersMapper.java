package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface UsersMapper {
    Users getUserByOpenid(@Param("openid") String openid);

    void insertUser(Users user);

    ArrayList<Users> getAllUsers();

    void updateInformation(@Param("openid") String openid, @Param("name") String name, @Param("phone") String phone);
}
