package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.dto.param.GetAllPictureParam;
import com.hanzoy.yuekewei.pojo.dto.result.GetAllPictureResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetCoachResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetSwiperResult;

/**
 * 静态资源管理
 */
public interface StaticResourceService {

    /**
     * 获取滑块层图片地址
     * @return 图片地址数组返回包装类
     */
    GetSwiperResult getSwiper();

    /**
     * 查看所有的图片
     * @param param 参数
     * @return 返回对象
     */
    GetAllPictureResult getAllPicture(GetAllPictureParam param);

}
