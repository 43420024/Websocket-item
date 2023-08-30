package com.example.websocketitem.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.SystemTelephone;
import com.example.websocketitem.service.SystemTelephoneService;
import com.example.websocketitem.mapper.SystemTelephoneMapper;
import com.example.websocketitem.utils.CheckUtils;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author cd
* @description 针对表【tcd_system_telephone(系统电话表)】的数据库操作Service实现
* @createDate 2023-08-30 16:03:15
*/
@Service
public class SystemTelephoneServiceImpl extends ServiceImpl<SystemTelephoneMapper, SystemTelephone>
    implements SystemTelephoneService{
    /**
     * 添加一个电话
     * */
    @Override
    public Result systemTelephoneAdd(SystemTelephone systemTelephone) {
        String phone = systemTelephone.getPhoneNumber();
        QueryWrapper<SystemTelephone> queryWrapper=new QueryWrapper<>();
        SystemTelephone phoneNumber = this.getOne(queryWrapper.eq("phone_number", phone));
        if(ObjectUtil.isNull(phoneNumber)){
            systemTelephone.setCreateTime(new Date());
            boolean save = super.save(systemTelephone);
            //判断是否添加成功
            if(save){
                return Result.ok();
            }else {
                return Result.error();
            }
        }else {
            return Result.error();
        }
    }
    /**
     * 删除一个电话
     * */
    @Override
    public Result systemTelephoneDelete(Integer id) {
        boolean b = super.removeById(id);
        return b?Result.ok():Result.error();
    }
}




