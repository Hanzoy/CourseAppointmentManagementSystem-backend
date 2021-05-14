package com.hanzoy.yuekewei.pojo.bo;

import lombok.Data;

@Data
public class UserTokenInfo {
    /**
     * 用户openid
     */
    private String openid;

    /**
     * 用户姓名
     */
    private String name;
}
