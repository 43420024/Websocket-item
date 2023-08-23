package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Trends;
import com.example.websocketitem.service.TrendsService;
import com.example.websocketitem.mapper.TrendsMapper;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_trends(用户动态表)】的数据库操作Service实现
* @createDate 2023-08-23 10:00:33
*/
@Service
public class TrendsServiceImpl extends ServiceImpl<TrendsMapper, Trends>
    implements TrendsService{

}




