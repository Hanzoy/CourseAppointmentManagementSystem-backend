package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.dto.param.AddCoachParam;
import com.hanzoy.yuekewei.pojo.dto.param.DeleteCoachParam;
import com.hanzoy.yuekewei.pojo.dto.param.EditCoachParam;
import com.hanzoy.yuekewei.pojo.dto.result.AddCoachResult;
import com.hanzoy.yuekewei.pojo.dto.result.DeleteCoachResult;
import com.hanzoy.yuekewei.pojo.dto.result.EditCoachResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetCoachResult;

import java.util.List;

/**
 * 教练业务层
 */
public interface CoachService {
    /**
     * 获取教练信息
     * @return 教练信息返回包装类
     */
    GetCoachResult getCoach();

    DeleteCoachResult deleteCoach(DeleteCoachParam param);

    AddCoachResult addCoach(AddCoachParam param);

    EditCoachResult editCoach(EditCoachParam param);
}
