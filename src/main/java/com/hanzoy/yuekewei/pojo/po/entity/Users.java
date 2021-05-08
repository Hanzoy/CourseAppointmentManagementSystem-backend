package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

/**
 * 数据库users表实体类
 */
@Data
public class Users {
    /**
     * 用户openid
     */
    private String openid;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户微信昵称
     */
    private String nickName;

    /**
     * 用户微信头像
     */
    private String avatarUrl;

    /**
     * 用户电话
     */
    private String phone;
}
