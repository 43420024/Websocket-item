package com.example.websocketitem.controller;

import com.example.websocketitem.model.Report;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.SearchModel;
import com.example.websocketitem.service.ReportService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Resource
    ReportService reportService;
    /**
     * 添加举报信息
     * */
    @PostMapping
    public ResponseMap addReport(@RequestBody Report report){
        return reportService.addReport(report);
    }
    /**
     * 跟新举报信息处理状态
     * */
    @PutMapping
    public ResponseMap updateReport(@RequestBody Report report){
        return reportService.updateReport(report);
    }
    /**
     * 删除举报信息(用户撤销举报
     * */
    @DeleteMapping("/{id}")
    public ResponseMap deleteReport(@PathVariable Long id){
        return reportService.deleteReport(id);
    }
    /**
     * 举报信息分页列表获取
     * */
    @GetMapping("/list/{page}/{size}")
    public ResponseMap listReport(@PathVariable Integer page,@PathVariable Integer size){
        return reportService.listReport(page,size);
    }
    /**
     * 全字段模糊查询+按时间获取举报信息列表
     * */
    @GetMapping("/search")
    public ResponseMap searchReport(@RequestBody SearchModel searchModel){
        return reportService.searchReport(searchModel);
    }
    /**
     * 获取被举报人审核确认违规信息统计
     * */
    @GetMapping("/countReporter/{reporterId}")
    public ResponseMap countReport(@PathVariable Long reporterId){
        return reportService.countReport(reporterId);
    }
    /**
     * 根据用户编号和分页信息获取未审核举报分页列表
     * */
    @GetMapping("/pageList/{reporterId}/{page}/{size}")
    public ResponseMap pageListReport(@PathVariable Long reporterId,
                                    @PathVariable Integer page,
                                    @PathVariable Integer size){
        return reportService.pageListReport(reporterId,page,size);
    }
    /**
     * 获取单一举报信息
     * */
    @GetMapping("/{id}")
    public ResponseMap getReport(@PathVariable Long id){
        return reportService.getReport(id);
    }
}
