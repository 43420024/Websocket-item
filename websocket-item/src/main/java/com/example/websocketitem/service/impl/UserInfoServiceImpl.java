package com.example.websocketitem.service.impl;


import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.domain.UserInfo;
import com.example.websocketitem.service.UserInfoService;
import com.example.websocketitem.mapper.UserInfoMapper;
import com.example.websocketitem.utils.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_user_info】的数据库操作Service实现
* @createDate 2023-08-26 11:05:58
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{


    @Override
    public Result add(UserInfo userInfo) {

        userInfo.setLabels(JSON.toJSONString(userInfo.getLabelsArray()));
        final int insert = this.baseMapper.insert(userInfo);

        return insert>0?Result.success():Result.error();
    }
}




