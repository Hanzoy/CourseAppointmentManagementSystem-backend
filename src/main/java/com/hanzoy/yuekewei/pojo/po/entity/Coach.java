package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

/**
 * 数据库coach表实体类
 */
@Data
public class Coach {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 描述
     */
    private String content;

    /**
     * 头像图片对应picture表中主键
     */
    private Integer avatarUrl;

    /**
     * 背景图片对应picture表中主键
     */
    private Integer backgroundUrl;
}
