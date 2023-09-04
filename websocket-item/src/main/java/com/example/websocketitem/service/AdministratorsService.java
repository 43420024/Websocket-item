package com.example.websocketitem.service;

import com.example.websocketitem.model.Administrators;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_administrators(管理员表)】的数据库操作Service
* @createDate 2023-08-30 14:18:26
*/
public interface AdministratorsService extends IService<Administrators> {
    Result userLogon(Long manageAccounts,String passwords);
}
