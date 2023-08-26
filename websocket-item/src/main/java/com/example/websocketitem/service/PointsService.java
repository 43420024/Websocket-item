package com.example.websocketitem.service;

import com.example.websocketitem.model.Points;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;

public interface PointsService {
    Result<PageInfo<Points>> selectAll(Integer pageNum, Integer pageSize);
}
