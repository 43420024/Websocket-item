package com.example.websocketitem.controller;

import com.example.websocketitem.model.PointsRules;
import com.example.websocketitem.service.PointsRulesService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("pointsRules")
public class PointsRulesController<T> {
    @Resource
    private PointsRulesService<T> pointsRulesService;
    @GetMapping
    public Result<List<PointsRules>> listPointsRules() {
        return this.pointsRulesService.selectAll();
    }
    @PutMapping
    public Result<T> updatePointsRules(@RequestBody PointsRules pointsRules) {
        return this.pointsRulesService.updateByPrimaryKey(pointsRules);
    }
    @PostMapping
    public Result<T> addPointsRules(@RequestBody PointsRules pointsRules) {
        return this.pointsRulesService.insert(pointsRules);
    }
    @DeleteMapping("/{id}")
    public Result<T> deletePointsRules(@PathVariable Long id) {
        return this.pointsRulesService.deleteByPrimaryKey(id);
    }
    @GetMapping("/{id}")
    public Result<PointsRules> getPointsRules(@PathVariable Long id) {
        return this.pointsRulesService.selectByPrimaryKey(id);
    }
}
