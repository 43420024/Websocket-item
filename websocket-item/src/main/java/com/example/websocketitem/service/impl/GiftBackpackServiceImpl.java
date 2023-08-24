package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.domain.GiftBackpack;
import com.example.websocketitem.service.GiftBackpackService;
import com.example.websocketitem.mapper.GiftBackpackMapper;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
* @author cd
* @description 针对表【tcd_gift_backpack】的数据库操作Service实现
* @createDate 2023-08-24 08:49:02
*/
@Service
public class GiftBackpackServiceImpl extends ServiceImpl<GiftBackpackMapper, GiftBackpack>
    implements GiftBackpackService{



    @Override
    public Result addGift(GiftBackpack giftBackpack) {
         QueryWrapper<GiftBackpack> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",giftBackpack.getUserId())
                .eq("gift_id",giftBackpack.getGiftId());
         GiftBackpack gift = this.baseMapper.selectOne(queryWrapper);

        if (gift!=null){
            gift.setNumber(gift.getNumber()+giftBackpack.getNumber());
            gift.setBuyTime(LocalDateTime.now());
            final int num = this.baseMapper.updateById(gift);
            return num>0?Result.success():Result.error();
         }
        giftBackpack.setBuyTime(LocalDateTime.now());
         int insert = this.baseMapper.insert(giftBackpack);
        return insert>0?Result.success():Result.error();
    }
}




