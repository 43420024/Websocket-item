package com.example.websocketitem.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
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
        return Result.ok("查询成功", pageInfo);
    }

    @Override
    public Result<T> deleteByPrimaryKey(Long id) {
        int deleteByPrimaryKey = this.pointsMapper.deleteByPrimaryKey(id);
        if (deleteByPrimaryKey>0){
            return Result.ok("删除成功");
        }
        return Result.error("删除失败");
    }

    @Override
    public Result<T> insert(Points record) {
        int insert = this.pointsMapper.insert(record);
        if (insert>0){
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");
    }

    @Override
    public Result<T> insertSelective(Points record) {
        int insertSelective = this.pointsMapper.insertSelective(record);
        if (insertSelective>0){
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");
    }

    @Override
    public Result<Points> selectByPrimaryKey(Long id) {
        Points selectByPrimaryKey = this.pointsMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNotEmpty(selectByPrimaryKey)){
            return Result.ok("查询成功", selectByPrimaryKey);
        }
        return Result.error("查询失败");
    }

    @Override
    public Result<T> updateByPrimaryKeySelective(Points record) {
        int updateByPrimaryKeySelective = this.pointsMapper.updateByPrimaryKeySelective(record);
        if (updateByPrimaryKeySelective>0){
            return Result.ok("修改成功");
        }
        return Result.error("修改失败");
    }

    @Override
    public Result<T> updateByPrimaryKey(Points record) {
        int updateByPrimaryKey = this.pointsMapper.updateByPrimaryKey(record);
        if (updateByPrimaryKey>0){
            return Result.ok("修改成功");
        }
        return Result.error("修改失败");
    }

    @Override
    public Result<Object> updateTotalPointsByUserId(Long userId, Integer addPoints) {
        Points oneByUserId = this.pointsMapper.selectOneByUserId(userId);
        if(ObjectUtil.isNull(oneByUserId)){
            return Result.error("用户不存在");
        }
        Integer totalPoints = oneByUserId.getTotalPoints();
        totalPoints = totalPoints+addPoints;
        oneByUserId.setTotalPoints(totalPoints);

        Map<String, Object> points = MapUtil.builder(new HashMap<String, Object>()).put("userId", userId)
                .put("totalPoints", totalPoints).build();
        int updateTotalPointsByUserId = this.pointsMapper.updateTotalPointsByUserId(totalPoints, userId);
        if (updateTotalPointsByUserId>0){
            return Result.ok("积分添加成功",points);
        }
        return Result.error("积分添加失败");
    }
}
