package com.example.websocketitem.service;

import com.example.websocketitem.model.Report;
import com.example.websocketitem.model.Report;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.SearchModel;
import com.example.websocketitem.utils.DataType;

import java.util.List;

/**
* @author cd
* @description 针对表【tcd_report(举报信息)】的数据库操作Service
* @createDate 2023-08-25 17:34:11
*/
public interface ReportService extends IService<Report> {
    /**
     * 添加举报信息
     * */
    ResponseMap addReport(Report report);
    /**
     * 跟新举报信息处理状态
     * */
    ResponseMap updateReport(Report report);
    /**
     * 举报信息分页列表获取
     * */
    ResponseMap listReport(Integer page,Integer size);
    /**
     * 删除举报信息(用户撤销举报
     * */
    ResponseMap deleteReport(Long id);
    /**
     * 全字段模糊查询+按时间获取举报信息列表
     * */
    ResponseMap searchReport(SearchModel searchModel);
    /**
     * 获取被举报人举报信息统计
     * */
    ResponseMap countReport(Long reporterId);
    /**
     * 获取未处理举报信息用户编号及该用户未处理举报信息个数
     * */
    //ResponseMap statReport();
    /**
     * 根据用户编号和分页信息获取未审核举报分页列表
     * */
    ResponseMap pageListReport(Long reporterId,Integer page,Integer size);
    /**
     * 获取单一举报信息
     * */
    ResponseMap getReport(Long id);

    //查询举报信息未动态举报的用户列表
    DataType typeByTrendsList(Long userid, Integer type);
    /**
     * 获取被举报用户编号工具方法
     * */
    //List<Report> getReporterIdList();
}
