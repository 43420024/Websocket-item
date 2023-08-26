package com.example.websocketitem.controller;

import com.example.websocketitem.model.Points;
import com.example.websocketitem.service.PointsService;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("points")
public class PointsController {
    @Resource
    private PointsService pointsService;
    @GetMapping
    public Result<PageInfo<Points>> listAllPage(@RequestParam(defaultValue = "1") int pageNum,
                                                @RequestParam(defaultValue = "10") int pageSize) {
        return pointsService.selectAll(pageNum, pageSize);
    }
}
