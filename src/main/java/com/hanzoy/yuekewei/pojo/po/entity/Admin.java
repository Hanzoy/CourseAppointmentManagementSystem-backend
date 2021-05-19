package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

@Data
public class Admin {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 名字
     */
    private String name;
}
