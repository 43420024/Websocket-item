package com.example.websocketitem.service;

import com.example.websocketitem.domain.GiftType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_gift_type】的数据库操作Service
* @createDate 2023-08-25 14:53:56
*/
public interface GiftTypeService extends IService<GiftType> {

    Result add(GiftType giftType);

    Result updateType(GiftType giftType);
}
