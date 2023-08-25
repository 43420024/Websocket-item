package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.domain.Gift;
import com.example.websocketitem.domain.GiftType;
import com.example.websocketitem.service.GiftService;
import com.example.websocketitem.mapper.GiftMapper;
import com.example.websocketitem.service.GiftTypeService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author cd
 * @description 针对表【tcd_gift】的数据库操作Service实现
 * @createDate 2023-08-23 10:56:33
 */
@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift>
        implements GiftService {

    @Resource
    private GiftTypeService giftTypeService;

    @Override
    public Result select(Integer pageNum, Integer pageSize, Integer type) {
        QueryWrapper<Gift> wrapper = new QueryWrapper<>();
        if (type != null) {
            wrapper.eq("type", type);
        }
        Page<Gift> giftPage = new Page<>(pageNum, pageSize);
        Page<Gift> page = this.baseMapper.selectPage(giftPage, wrapper);
        for (Gift record : page.getRecords()) {
            GiftType giftType = giftTypeService.getById(record.getTypeId());
            record.setGiftType(giftType);
        }
        return Result.success(page);

    }

    @Override
    public Result addGift(Gift gift) {
        QueryWrapper<Gift> wrapper = new QueryWrapper<>();
        wrapper.eq("name", gift.getName());
        boolean exists = this.baseMapper.exists(wrapper);
        if (exists) {
            return Result.error("礼物名称已存在！");
        }
        gift.setCreateTime(LocalDateTime.now());
        gift.setUpdateTime(LocalDateTime.now());
        int insert = this.baseMapper.insert(gift);
        return insert > 0 ? Result.success("添加礼物成功！") : Result.error("添加礼物失败！");
    }

    @Override
    public Result updateGift(Gift gift) {
        QueryWrapper<Gift> wrapper = new QueryWrapper<>();
        wrapper.eq("name", gift.getName());
        boolean exists = this.baseMapper.exists(wrapper);
        if (exists) {
            wrapper.eq("id", gift.getId());
            boolean flag = this.baseMapper.exists(wrapper);
            if (!flag) {
                return Result.error("礼物名称已存在！");
            }
        }
        gift.setUpdateTime(LocalDateTime.now());
        int update = this.baseMapper.updateById(gift);
        return update > 0 ? Result.success("修改成功！") : Result.error("修改失败！");
    }

    @Override
    public Result queryOne(Integer id) {
        Gift gift = this.baseMapper.selectById(id);
        GiftType giftType = giftTypeService.getById(gift.getTypeId());
        gift.setGiftType(giftType);
        return Result.success(gift);
    }
}




