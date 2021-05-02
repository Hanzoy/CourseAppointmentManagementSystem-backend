package com.hanzoy.yuekewei.pojo.po;

import lombok.Data;

/**
 * 封装查询结果
 */
@Data
public class CoachInfo {
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
     * 头像图片url
     */
    private String avatarUrl;

    /**
     * 背景图片url
     */
    private String backgroundUrl;
}
