package com.example.websocketitem.service;

import com.example.websocketitem.domain.GiftBackpack;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_gift_backpack】的数据库操作Service
* @createDate 2023-08-24 08:49:02
*/
public interface GiftBackpackService extends IService<GiftBackpack> {

    Result addGift(GiftBackpack giftBackpack);

    Result select(Long userId);
}
