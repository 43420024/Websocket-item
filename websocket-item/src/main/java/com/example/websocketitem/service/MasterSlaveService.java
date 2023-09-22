package com.example.websocketitem.service;

import com.example.websocketitem.model.MasterSlaveRelationship;
import com.example.websocketitem.model.User;
import com.example.websocketitem.model.UserInfo;
import com.example.websocketitem.utils.Result;

import java.util.List;

public interface MasterSlaveService {
    Long getParentIdByUserId(Long userId);

    Result<List<MasterSlaveRelationship>> listMasterSlaves(Long parentId);

    Result addUserAndMasterSlave(UserInfo user, Long parentId);

    Result updateRelation(Long userId, Long parentId);

    Result deleteRelation(Long userId);

    Result deleteRelationAndUser(Long userId);
}
