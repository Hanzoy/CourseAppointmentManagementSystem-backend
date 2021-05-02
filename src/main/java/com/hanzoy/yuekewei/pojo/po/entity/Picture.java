package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据库picture表实体类
 */
@Data
@AllArgsConstructor
public class Picture {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 图床url
     */
    private String url;

    /**
     * 备注
     */
    private String remark;
}
