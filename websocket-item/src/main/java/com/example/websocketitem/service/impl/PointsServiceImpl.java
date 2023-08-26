package com.example.websocketitem.service.impl;

import com.example.websocketitem.mapper.PointsMapper;
import com.example.websocketitem.model.Points;
import com.example.websocketitem.service.PointsService;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PointsServiceImpl implements PointsService {
    @Resource
    private PointsMapper pointsMapper;
    @Override
    public Result<PageInfo<Points>> selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Points> pointsList = pointsMapper.selectAll();
        PageInfo<Points> pageInfo = new PageInfo<>(pointsList);
        return Result.ok("查询成功", pageInfo);
    }
}
