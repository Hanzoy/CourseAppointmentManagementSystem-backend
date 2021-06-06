package com.hanzoy.yuekewei.pojo.po;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserInfo {
    private String name;
    private String nickName;
    private String avatarUrl;
    private String phone;
    private String remark;
    private ArrayList<CourseInfo> course;
    private ArrayList<OperationInfo> operation;
}
