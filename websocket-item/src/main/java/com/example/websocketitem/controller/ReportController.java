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
    @PostMapping
    public ResponseMap addReport(@RequestBody Report report){
        return reportService.addReport(report);
    }
    @PutMapping
    public ResponseMap updateReport(@RequestBody Report report){
        return reportService.updateReport(report);
    }
    @DeleteMapping("/{id}")
    public ResponseMap deleteReport(@PathVariable Long id){
        return reportService.deleteReport(id);
    }
    @GetMapping("/list/{page}/{size}")
    public ResponseMap listReport(@PathVariable Integer page,@PathVariable Integer size){
        return reportService.listReport(page,size);
    }
    @GetMapping("/search")
    public ResponseMap searchReport(@RequestBody SearchModel searchModel){
        return reportService.searchReport(searchModel);
    }
    @GetMapping("/countReporter/{reporterId}")
    public ResponseMap countReport(@PathVariable Long reporterId){
        return reportService.countReport(reporterId);
    }
}
