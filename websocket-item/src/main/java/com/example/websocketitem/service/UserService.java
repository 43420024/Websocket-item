package com.example.websocketitem.service;

import com.example.websocketitem.domain.Data;
import com.example.websocketitem.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_user(用户表)】的数据库操作Service
* @createDate 2023-08-26 11:05:58
*/
public interface UserService extends IService<User> {

    Result updateStatus(User user);

    Result selectPage(Integer pageNum, Integer pageSize, Integer gender, Integer phoneNumber, Integer nickname);

    Result updateUser(Data data);
}
