package com.example.websocketitem.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.websocketitem.enums.ResponseStatusEnum;
import com.example.websocketitem.mapper.PointsRulesMapper;
import com.example.websocketitem.model.PointsRules;
import com.example.websocketitem.service.PointsRulesService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PointsRulesServiceImpl<T> implements PointsRulesService<T> {
    @Resource
    private PointsRulesMapper pointsRulesMapper;
    @Override
    public Result<T> deleteByPrimaryKey(Long id) {
        int deleteByPrimaryKey = pointsRulesMapper.deleteByPrimaryKey(id);
        return deleteByPrimaryKey > 0 ? Result.ok(ResponseStatusEnum.DELETE_SUCCESS) : Result.error(ResponseStatusEnum.DELETE_FAILURE);
    }

    @Override
    public Result<T> insert(PointsRules record) {
        int insert = this.pointsRulesMapper.insert(record);
        return insert > 0 ? Result.ok(ResponseStatusEnum.ADD_SUCCESS) : Result.error(ResponseStatusEnum.ADD_FAILURE);
    }

    @Override
    public Result<T> insertSelective(PointsRules record) {
        int insertSelective = this.pointsRulesMapper.insertSelective(record);
        return insertSelective > 0 ? Result.ok(ResponseStatusEnum.ADD_SUCCESS) : Result.error(ResponseStatusEnum.ADD_FAILURE);
    }

    @Override
    public Result<PointsRules> selectByPrimaryKey(Long id) {
        PointsRules pointsRules = this.pointsRulesMapper.selectByPrimaryKey(id);
        return pointsRules != null ? Result.ok(ResponseStatusEnum.QUERY_SUCCESS) : Result.error(ResponseStatusEnum.QUERY_FAILURE);
    }

    @Override
    public Result<T> updateByPrimaryKeySelective(PointsRules record) {
        int updateByPrimaryKeySelective = this.pointsRulesMapper.updateByPrimaryKeySelective(record);
        return updateByPrimaryKeySelective > 0 ? Result.ok(ResponseStatusEnum.UPDATE_SUCCESS) : Result.error(ResponseStatusEnum.UPDATE_FAILURE);
    }

    @Override
    public Result<T> updateByPrimaryKey(PointsRules record) {
        int updateByPrimaryKey = this.pointsRulesMapper.updateByPrimaryKey(record);
        return updateByPrimaryKey > 0 ? Result.ok(ResponseStatusEnum.UPDATE_SUCCESS) : Result.error(ResponseStatusEnum.UPDATE_FAILURE);
    }

    @Override
    public Result<List<PointsRules>> selectAll() {
        List<PointsRules> pointsRules = this.pointsRulesMapper.selectAll();
        return Result.ok(ResponseStatusEnum.QUERY_SUCCESS,pointsRules);
    }
}
