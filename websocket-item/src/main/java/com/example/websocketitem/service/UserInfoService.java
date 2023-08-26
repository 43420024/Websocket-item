package com.example.websocketitem.service;

import com.example.websocketitem.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_user_info】的数据库操作Service
* @createDate 2023-08-26 11:05:58
*/
public interface UserInfoService extends IService<UserInfo> {

    Result add(UserInfo userInfo);
}
