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


    @Override
    public ResponseMap addReport(Report report) {
        report.setCreateTime(new Date());
        return responseMapUtil.addEntity(this.save(report));
    }
    //TODO 更改状态后通知检举人举报信息处理结果
    @Override
    public ResponseMap updateReport(Report report) {
        return responseMapUtil.updateEntity(this.updateById(report));
    }

    @Override
    public ResponseMap listReport(Integer page, Integer size) {
        Page<Report> pageList = this.page(pageUtil.getModelPage(page, size));
        Map<String, Object> modelMap = pageUtil.getModelMap(pageList);
        return responseMapUtil.getPageList(pageList,modelMap);
    }

    @Override
    public ResponseMap deleteReport(Long id) {
        return responseMapUtil.deleteList(this.removeById(id));
    }

    @Override
    public ResponseMap searchReport(SearchModel searchModel) {
        Page<Report> pageList = this.page(pageUtil.getModelPage(searchModel.getPage(), searchModel.getSize()),
                wrapperUtil.wrapperReport(searchModel.getSearch(), searchModel.getStartTime(), searchModel.getEndTime()));
        Map<String, Object> modelMap = pageUtil.getModelMap(pageList);
        return responseMapUtil.getPageList(pageList,modelMap);
    }

    @Override
    public ResponseMap countReport(Long reporterId) {
        List<Report> list = this.list(wrapperUtil.countReport(reporterId));
        return null;
    }
}




