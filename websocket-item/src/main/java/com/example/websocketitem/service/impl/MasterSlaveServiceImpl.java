package com.example.websocketitem.service.impl;

import com.example.websocketitem.mapper.MasterSlaveRelationshipMapper;
import com.example.websocketitem.mapper.UserInfoMapper;
import com.example.websocketitem.mapper.UserMapper;
import com.example.websocketitem.model.MasterSlaveRelationship;
import com.example.websocketitem.model.User;
import com.example.websocketitem.model.UserInfo;
import com.example.websocketitem.service.MasterSlaveService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class MasterSlaveServiceImpl implements MasterSlaveService {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private MasterSlaveRelationshipMapper masterSlaveRelationshipMapper;

    @Override
    public Long getParentIdByUserId(Long userId) {
        return masterSlaveRelationshipMapper.selectParentIdByUserId(userId);
    }

    @Override
    public Result<List<MasterSlaveRelationship>> listMasterSlaves(Long parentId) {
        List<MasterSlaveRelationship> listMasterSlaves = masterSlaveRelationshipMapper.listMasterSlaves(parentId);
        return Result.ok("查询成功", listMasterSlaves);
    }

    @Override
    public Result addUserAndMasterSlave(UserInfo user, Long parentId) {
        user.setCreateTime(new Date());
        int insert = userInfoMapper.insert(user);
        if (insert > 0) {
            MasterSlaveRelationship relationship = new MasterSlaveRelationship(null, user.getInfoId(), parentId, LocalDateTime.now(),null);
            int insertRelationShip = masterSlaveRelationshipMapper.insert(relationship);
            return insertRelationShip > 0 ? Result.ok() : Result.error();
        }
        return Result.error();
    }

    @Override
    public Result updateRelation(Long userId, Long parentId) {
        int updateParentIdByUserId = masterSlaveRelationshipMapper.updateParentIdByUserId(parentId, userId);
        return updateParentIdByUserId > 0 ? Result.ok() : Result.error();
    }

    @Override
    public Result deleteRelation(Long userId) {
        int deleteByUserId = masterSlaveRelationshipMapper.deleteByUserId(userId);
        return deleteByUserId > 0 ? Result.ok() : Result.error();
    }

    @Override
    public Result deleteRelationAndUser(Long userId) {
        int deleteByUserId = masterSlaveRelationshipMapper.deleteByUserId(userId);
        if (deleteByUserId > 0) {
            int deleteById = userInfoMapper.deleteById(userId);
            return deleteById > 0 ? Result.ok() : Result.error("关系删除成功，但用户表用户id不存在，删除用户账号失败");
        }
        return Result.error("删除失败");
    }
}
