package com.example.websocketitem.service;

import com.example.websocketitem.model.Points;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;

public interface PointsService<T> {
    Result<PageInfo<Points>> selectAll(Integer pageNum, Integer pageSize);

    Result<T> deleteByPrimaryKey(Long id);

    Result<T> insert(Points record);

    Result<T> insertSelective(Points record);

    Result<Points> selectByPrimaryKey(Long id);

    Result<T> updateByPrimaryKeySelective(Points record);

    Result<T> updateByPrimaryKey(Points record);
}
