package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

/**
 * 数据库users_role_merge实体类
 */
@Data
public class UsersRoleMerge {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户opneid
     */
    private String userId;

    /**
     * role表id
     */
    private Integer roleId;
}
