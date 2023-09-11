package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Album;
import com.example.websocketitem.model.Report;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.SearchModel;
import com.example.websocketitem.service.AlbumPictureService;
import com.example.websocketitem.service.ReportService;
import com.example.websocketitem.mapper.ReportMapper;
import com.example.websocketitem.utils.PageUtil;
import com.example.websocketitem.utils.ResponseMapUtil;
import com.example.websocketitem.utils.WrapperUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author cd
* @description 针对表【tcd_report(举报信息)】的数据库操作Service实现
* @createDate 2023-08-25 17:34:11
*/
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report>
    implements ReportService{
    @Resource
    ResponseMapUtil<Report> responseMapUtil;
    @Resource
    PageUtil<Report> pageUtil;
    @Resource
    WrapperUtil<Report> wrapperUtil;

    /**
     * 添加举报信息
     * */
    @Override
    public ResponseMap addReport(Report report) {
        report.setCreateTime(new Date());
        return responseMapUtil.addEntity(this.save(report));
    }
    /**
     * 跟新举报信息处理状态
     * */
    //TODO 更改状态后通知检举人举报信息处理结果
    @Override
    public ResponseMap updateReport(Report report) {
        return responseMapUtil.updateEntity(this.updateById(report));
    }
    /**
     * 举报信息分页列表获取
     * */
    @Override
    public ResponseMap listReport(Integer page, Integer size) {
        Page<Report> pageList = this.page(pageUtil.getModelPage(page, size));
        Map<String, Object> modelMap = pageUtil.getModelMap(pageList);
        return responseMapUtil.getPageList(pageList,modelMap);
    }
    /**
     * 删除举报信息(用户撤销举报
     * */
    @Override
    public ResponseMap deleteReport(Long id) {
        return responseMapUtil.deleteList(this.removeById(id));
    }
    /**
     * 全字段模糊查询+按时间获取举报信息列表
     * */
    @Override
    public ResponseMap searchReport(SearchModel searchModel) {
        Page<Report> pageList = this.page(pageUtil.getModelPage(searchModel.getPage(), searchModel.getSize()),
                wrapperUtil.wrapperReport(searchModel.getSearch(), searchModel.getStartTime(), searchModel.getEndTime()));
        Map<String, Object> modelMap = pageUtil.getModelMap(pageList);
        return responseMapUtil.getPageList(pageList,modelMap);
    }
    /**
     * 获取被举报人举报信息统计
     * */
    @Override
    public ResponseMap countReport(Long reporterId) {
        return responseMapUtil.countNumber(this.list(wrapperUtil.countReport(reporterId)).size());
    }
    /**
     * 获取未处理举报信息用户编号及该用户未处理举报信息个数
     * */
    @Override
    public ResponseMap statReport() {
        List<Report> reportList = this.list(wrapperUtil.groupByReporterId());
        Map<Long,Integer> map = new HashMap<>();
        reportList.forEach(report-> map.put(report.getReporterId(), (int) this.count(wrapperUtil.wrapperReporterId(report.getReporterId()))));
        return responseMapUtil.returnMap(map);
    }
    /**
     * 根据用户编号和分页信息获取未审核举报分页列表
     * */
    @Override
    public ResponseMap pageListReport(Long reporterId, Integer page, Integer size) {
        Page<Report> pageList = this.page(pageUtil.getModelPage(page, size), wrapperUtil.wrapperReporterId(reporterId));
        Map<String, Object> modelMap = pageUtil.getModelMap(pageList);
        return responseMapUtil.getPageList(pageList,modelMap);
    }
    /**
     * 获取单一举报信息
     * */
    @Override
    public ResponseMap getReport(Long id) {
        return responseMapUtil.getEntity(this.getById(id));
    }
}




