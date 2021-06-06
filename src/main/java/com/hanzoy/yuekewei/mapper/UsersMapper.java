package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.UserInfo;
import com.hanzoy.yuekewei.pojo.po.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface UsersMapper {
    Users getUserByOpenid(@Param("openid") String openid);

    void insertUser(Users user);

    ArrayList<Users> getAllUsers();

    void updateInformation(@Param("openid") String openid, @Param("name") String name, @Param("phone") String phone, @Param("remark") String remark);

    void updateInformationWithoutRemark(@Param("openid") String openid, @Param("name") String name, @Param("phone") String phone);

    ArrayList<Users> getAllUsersByKey(@Param("key") String key);

    UserInfo getUserInfo(@Param("openid") String openid);

    void updateCourseTime(@Param("openid")String openid, @Param("courseId") Integer courseId, @Param("count") Double count);

    void addCourseTime(@Param("openid")String openid, @Param("courseId") Integer courseId, @Param("count") Double count);
}
