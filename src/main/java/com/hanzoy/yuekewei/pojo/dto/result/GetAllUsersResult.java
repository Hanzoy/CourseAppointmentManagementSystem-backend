package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.entity.Users;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetAllUsersResult {
    private ArrayList<Users> users;
}
