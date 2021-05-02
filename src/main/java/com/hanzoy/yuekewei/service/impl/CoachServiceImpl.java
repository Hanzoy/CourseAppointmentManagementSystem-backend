package com.hanzoy.yuekewei.service.impl;

import com.hanzoy.yuekewei.mapper.CoachMapper;
import com.hanzoy.yuekewei.pojo.dto.result.GetCoachResult;
import com.hanzoy.yuekewei.pojo.po.CoachInfo;
import com.hanzoy.yuekewei.service.CoachService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    @Resource
    CoachMapper coachMapper;

    @Override
    public GetCoachResult getCoach() {
        //查询教练信息
        List<CoachInfo> coach = coachMapper.getCoach();
        //创建返回类型
        GetCoachResult result = new GetCoachResult();
        //将教练信息写入返回对象中
        result.setCoachInfo(coach);
        return result;
    }
}
