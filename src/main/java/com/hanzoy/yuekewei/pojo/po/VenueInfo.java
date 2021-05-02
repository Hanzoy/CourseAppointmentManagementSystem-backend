package com.hanzoy.yuekewei.pojo.po;

import lombok.Data;

@Data
public class VenueInfo {
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
     * 场馆图片
     */
    private String pictureUrl;
}
