package com.example.websocketitem.service;

import com.example.websocketitem.domain.Gift;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_gift】的数据库操作Service
* @createDate 2023-08-23 10:56:33
*/
public interface GiftService extends IService<Gift> {

    Result select(Integer pageNum, Integer pageSize,Integer type,Integer status,String name);

    Result addGift(Gift gift);

    Result updateGift(Gift gift);

    Result queryOne(Integer id);

    Result updateStatus(Gift gift);
}
