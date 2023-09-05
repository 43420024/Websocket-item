package com.example.websocketitem.service;

import com.example.websocketitem.model.PointsRules;
import com.example.websocketitem.utils.Result;

import java.util.List;

public interface PointsRulesService<T> {
    Result<T> deleteByPrimaryKey(Long id);

    Result<T> insert(PointsRules record);

    Result<T> insertSelective(PointsRules record);

    Result<PointsRules> selectByPrimaryKey(Long id);

    Result<T> updateByPrimaryKeySelective(PointsRules record);

    Result<T> updateByPrimaryKey(PointsRules record);

    Result<List<PointsRules>> selectAll();
}
