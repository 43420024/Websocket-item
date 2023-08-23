package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.domain.Gift;
import com.example.websocketitem.service.GiftService;
import com.example.websocketitem.mapper.GiftMapper;
import com.example.websocketitem.utils.Result;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_gift】的数据库操作Service实现
* @createDate 2023-08-23 10:56:33
*/
@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift>
    implements GiftService{

    @Override
    public Result select(Integer pageNum, Integer pageSize) {
        //QueryWrapper<Gift> wrapper = new QueryWrapper<>();
        Page<Gift> giftPage = new Page<>(pageNum, pageSize);
        Page<Gift> page = this.baseMapper.selectPage(giftPage, null);
        return Result.success(page);

    }
}




