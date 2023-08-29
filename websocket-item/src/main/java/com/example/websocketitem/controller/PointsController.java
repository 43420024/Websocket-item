package com.example.websocketitem.controller;

import com.example.websocketitem.model.Points;
import com.example.websocketitem.service.PointsService;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("points")
public class PointsController<T> {
    @Resource
    private PointsService<T> pointsService;
    @GetMapping
    public Result<PageInfo<Points>> listAllPage(@RequestParam(defaultValue = "1") int pageNum,
                                                @RequestParam(defaultValue = "10") int pageSize) {
        return pointsService.selectAll(pageNum, pageSize);
    }
    @GetMapping("/{id}")
    public Result<Points> getPoints(@PathVariable Long id){
        return pointsService.selectByPrimaryKey(id);
    }
    @PostMapping
    public Result<T> addSelectivePoints(@RequestBody @Valid Points points) {
        return pointsService.insertSelective(points);
    }
    @PutMapping
    public Result<T> updateSelectivePoints(@RequestBody @Valid Points points) {
        return pointsService.updateByPrimaryKeySelective(points);
    }
    @DeleteMapping("/{id}")
    public Result<T> deletePoints(@PathVariable Long id) {
        return pointsService.deleteByPrimaryKey(id);
    }
    @PutMapping("/addPoints")
    public Result<Object> addPoints(@RequestParam Long userId,@RequestParam Integer addPoints){
        return pointsService.updateTotalPointsByUserId(userId,addPoints);
    }

}
