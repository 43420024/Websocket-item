package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Administrators;
import com.example.websocketitem.service.AdministratorsService;
import com.example.websocketitem.mapper.AdministratorsMapper;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_administrators(管理员表)】的数据库操作Service实现
* @createDate 2023-08-30 14:18:26
*/
@Service
public class AdministratorsServiceImpl extends ServiceImpl<AdministratorsMapper, Administrators>
    implements AdministratorsService{
    @Resource
    private AdministratorsMapper administratorsMapper;
    @Override
    public Result userLogon(Long manageAccounts, String passwords) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("manage_accounts",manageAccounts);
        queryWrapper.eq("passwords",passwords);
        Administrators administrators = administratorsMapper.selectOne(queryWrapper);
        if(administratorsMapper.selectOne(queryWrapper)==null){
            return Result.error();
        }else {
            return Result.ok().data(administrators);
        }
    }
}




