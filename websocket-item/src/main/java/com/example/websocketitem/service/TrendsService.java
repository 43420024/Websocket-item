package com.example.websocketitem.service;

import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.Trends;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.model.TrendsList;
import com.example.websocketitem.utils.DataType;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
* @author cd
* @description 针对表【tcd_trends(用户动态表)】的数据库操作Service
* @createDate 2023-08-23 10:00:33
*/
public interface TrendsService extends IService<Trends> {

    DataType savaTrends(Trends trends);

    DataType deleteTrends(Long id);

    DataType allQueryTrends(Long id);

    DataType addCount(Long id);

    ResponseMap userListTrends(int page, int size, Long userid);

    DataType selectOneTrends(Long id, Long userid);

    DataType statusByTrendsList(TrendsList trendsList);
}
