package com.example.websocketitem.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.factory.EntityFactory;
import com.example.websocketitem.mapper.PointsMapper;
import com.example.websocketitem.model.Album;
import com.example.websocketitem.model.UserInfo;
import com.example.websocketitem.model.Points;
import com.example.websocketitem.model.Relationship;
import com.example.websocketitem.service.AlbumService;
import com.example.websocketitem.service.PointsService;
import com.example.websocketitem.service.RelationshipService;
import com.example.websocketitem.service.UserInfoService;
import com.example.websocketitem.mapper.UserInfoMapper;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author cd
 * @description 针对表【tcd_user_info】的数据库操作Service实现
 * @createDate 2023-08-26 11:05:58
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

    @Resource
    private AlbumService albumService;
    @Resource
    private PointsMapper pointsMapper;
    @Resource
    RelationshipService relationshipService;

    Relationship relationship = EntityFactory.createRelationship();

    @Override
    public Result addInfo(UserInfo userInfo) {
        //数组转string
        userInfo.setLabels(JSON.toJSONString(userInfo.getLabelsArray()));
        userInfo.setCreateTime(new Date());
        int insert = this.baseMapper.insert(userInfo);
        if (insert > 0) {
            if (ObjectUtil.equals(1, userInfo.getGender())) {
                Points points = new Points();
                points.setUserId(userInfo.getUserId());
                points.setCreateTime(LocalDateTime.now());
                pointsMapper.insertSelective(points);
            }
        }
        relationship.setOwnerId(userInfo.getInfoId());
        relationship.setFriendId(1L);
        relationshipService.addRelationship(relationship);
        return insert > 0 ? Result.success() : Result.error();
    }

    @Override
    public Result queryInfo(Long userId) {
        //用户基本信息
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        UserInfo userInfo = this.baseMapper.selectOne(queryWrapper);
        //string转json数组
        userInfo.setLabelsArray(JSON.parseArray(userInfo.getLabels()));
        //相册
        QueryWrapper<Album> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Album> list = albumService.list(wrapper);
        userInfo.setAlbums(list);
        //积分
        Points points = pointsMapper.selectOneByUserId(userId);
        userInfo.setPoints(points);
        return Result.success(userInfo);
    }

    @Override
    public Result updateInfo(UserInfo userInfo) {
        userInfo.setLabels(JSON.toJSONString(userInfo.getLabelsArray()));
        int update = this.baseMapper.updateById(userInfo);
        return update > 0 ? Result.success() : Result.error();
    }
}




