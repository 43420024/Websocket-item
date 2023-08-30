package com.example.websocketitem.service;

import com.example.websocketitem.model.SystemTelephone;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_system_telephone(系统电话表)】的数据库操作Service
* @createDate 2023-08-30 16:03:15
*/
public interface SystemTelephoneService extends IService<SystemTelephone> {
    Result systemTelephoneAdd(SystemTelephone systemTelephone);
    Result systemTelephoneDelete(Integer id);
}
