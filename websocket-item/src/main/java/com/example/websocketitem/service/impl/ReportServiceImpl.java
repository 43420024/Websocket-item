package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Report;
import com.example.websocketitem.service.ReportService;
import com.example.websocketitem.mapper.ReportMapper;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_report(举报信息)】的数据库操作Service实现
* @createDate 2023-08-25 17:34:11
*/
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report>
    implements ReportService{

}




