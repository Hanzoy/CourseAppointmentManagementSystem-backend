package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

/**
 * 数据库swiper_picture表实体类
 */
@Data
public class Venue {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 场馆名
     */
    private String name;

    /**
     * 场馆地址
     */
    private String address;

    /**
     * 场馆图片对应picture表中主键
     */
    private Integer pictureUrl;
}
