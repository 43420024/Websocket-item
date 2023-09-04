package com.example.websocketitem.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.websocketitem.enums.ResponseStatusEnum;
import com.example.websocketitem.mapper.PointsMapper;
import com.example.websocketitem.model.Points;
import com.example.websocketitem.service.PointsService;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PointsServiceImpl<T> implements PointsService<T> {
    @Resource
    private PointsMapper pointsMapper;
    @Override
    public Result<PageInfo<Points>> selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Points> pointsList = pointsMapper.selectAll();
        PageInfo<Points> pageInfo = new PageInfo<>(pointsList);
        return Result.ok(ResponseStatusEnum.QUERY_SUCCESS, pageInfo);
    }

    @Override
    public Result<T> deleteByPrimaryKey(Long id) {
        int deleteByPrimaryKey = this.pointsMapper.deleteByPrimaryKey(id);
        if (deleteByPrimaryKey>0){
            return Result.ok(ResponseStatusEnum.DELETE_SUCCESS);
        }
        return Result.error(ResponseStatusEnum.DELETE_FAILURE);
    }

    @Override
    public Result<T> insert(Points record) {
        int insert = this.pointsMapper.insert(record);
        if (insert>0){
            return Result.ok(ResponseStatusEnum.ADD_SUCCESS);
        }
        return Result.error(ResponseStatusEnum.ADD_FAILURE);
    }

    @Override
    public Result<T> insertSelective(Points record) {
        int insertSelective = this.pointsMapper.insertSelective(record);
        if (insertSelective>0){
            return Result.ok(ResponseStatusEnum.ADD_SUCCESS);
        }
        return Result.error(ResponseStatusEnum.ADD_FAILURE);
    }

    @Override
    public Result<Points> selectByPrimaryKey(Long id) {
        Points selectByPrimaryKey = this.pointsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(selectByPrimaryKey)){
            return Result.ok(ResponseStatusEnum.QUERY_SUCCESS, selectByPrimaryKey);
        }
        return Result.error(ResponseStatusEnum.QUERY_FAILURE);
    }

    @Override
    public Result<T> updateByPrimaryKeySelective(Points record) {
        int updateByPrimaryKeySelective = this.pointsMapper.updateByPrimaryKeySelective(record);
        if (updateByPrimaryKeySelective>0){
            return Result.ok(ResponseStatusEnum.UPDATE_SUCCESS);
        }
        return Result.error(ResponseStatusEnum.UPDATE_FAILURE);
    }

    @Override
    public Result<T> updateByPrimaryKey(Points record) {
        int updateByPrimaryKey = this.pointsMapper.updateByPrimaryKey(record);
        if (updateByPrimaryKey>0){
            return Result.ok(ResponseStatusEnum.UPDATE_SUCCESS);
        }
        return Result.error(ResponseStatusEnum.UPDATE_FAILURE);
    }

    @Override
    public Result<Object> updateTotalPointsByUserId(Long userId, Integer addPoints) {
        Points oneByUserId = this.pointsMapper.selectOneByUserId(userId);
        if(ObjectUtil.isNull(oneByUserId)){
            return Result.error(ResponseStatusEnum.USER_NOT_EXIST);
        }
        Integer totalPoints = oneByUserId.getTotalPoints();
        totalPoints = totalPoints+addPoints;
        oneByUserId.setTotalPoints(totalPoints);

        Map<String, Object> points = MapUtil.builder(new HashMap<String, Object>()).put("userId", userId)
                .put("totalPoints", totalPoints).build();
        int updateTotalPointsByUserId = this.pointsMapper.updateTotalPointsByUserId(totalPoints, userId);
        if (updateTotalPointsByUserId>0){
            return Result.ok(ResponseStatusEnum.POINTS_PLUS_SUCCESS,points);
        }
        return Result.error(ResponseStatusEnum.POINTS_PLUS_FAILURE);
    }
}
