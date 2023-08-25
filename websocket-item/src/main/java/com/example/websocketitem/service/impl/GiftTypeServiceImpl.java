package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.domain.Gift;
import com.example.websocketitem.domain.GiftType;
import com.example.websocketitem.service.GiftTypeService;
import com.example.websocketitem.mapper.GiftTypeMapper;
import com.example.websocketitem.utils.Result;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
* @author cd
* @description 针对表【tcd_gift_type】的数据库操作Service实现
* @createDate 2023-08-25 14:53:56
*/
@Service
public class GiftTypeServiceImpl extends ServiceImpl<GiftTypeMapper, GiftType>
    implements GiftTypeService{

    @Override
    public Result add(GiftType giftType) {
        final QueryWrapper<GiftType> wrapper = new QueryWrapper<>();
        wrapper.eq("type_name",giftType.getTypeName());
        final boolean exists = this.baseMapper.exists(wrapper);
        if (exists){
            return Result.error("类型名称已存在！");
        }
        giftType.setCreateTime(LocalDateTime.now());
        final int insert = this.baseMapper.insert(giftType);
        return insert>0?Result.success("添加成功"):Result.error("添加失败");
    }

    @Override
    public Result updateType(GiftType giftType) {
        QueryWrapper<GiftType> wrapper = new QueryWrapper<>();
        wrapper.eq("type_name", giftType.getTypeName());
        boolean exists = this.baseMapper.exists(wrapper);
        if (exists) {
            wrapper.eq("id", giftType.getId());
            boolean flag = this.baseMapper.exists(wrapper);
            if (!flag) {
                return Result.error("礼物名称已存在！");
            }
        }
        giftType.setCreateTime(LocalDateTime.now());
        int update = this.baseMapper.updateById(giftType);
        return update > 0 ? Result.success("修改成功！") : Result.error("修改失败！");
    }
}




