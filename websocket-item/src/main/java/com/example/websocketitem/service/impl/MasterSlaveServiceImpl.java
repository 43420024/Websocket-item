package com.example.websocketitem.service.impl;

import com.example.websocketitem.mapper.MasterSlaveRelationshipMapper;
import com.example.websocketitem.service.MasterSlaveService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MasterSlaveServiceImpl implements MasterSlaveService {
    @Resource
    private MasterSlaveRelationshipMapper masterSlaveRelationshipMapper;
    @Override
    public Long getParentIdByUserId(Long userId) {
        return masterSlaveRelationshipMapper.selectParentIdByUserId(userId);
    }
}
