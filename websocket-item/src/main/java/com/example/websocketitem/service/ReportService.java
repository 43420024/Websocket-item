package com.example.websocketitem.service;

import com.example.websocketitem.model.Report;
import com.example.websocketitem.model.Report;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.SearchModel;

/**
* @author cd
* @description 针对表【tcd_report(举报信息)】的数据库操作Service
* @createDate 2023-08-25 17:34:11
*/
public interface ReportService extends IService<Report> {
    ResponseMap addReport(Report report);

    ResponseMap updateReport(Report report);

    ResponseMap listReport(Integer page,Integer size);

    ResponseMap deleteReport(Long id);

    ResponseMap searchReport(SearchModel searchModel);

    ResponseMap countReport(Long reporterId);
}
