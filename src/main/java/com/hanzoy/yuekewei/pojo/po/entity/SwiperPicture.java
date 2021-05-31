package com.hanzoy.yuekewei.pojo.po.entity;

import lombok.Data;

/**
 * 数据库swiper_picture表实体类
 */
@Data
public class SwiperPicture {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 对应picture表中主键
     */
    private Integer pictureId;

    private String name;

    private String remark;
}
