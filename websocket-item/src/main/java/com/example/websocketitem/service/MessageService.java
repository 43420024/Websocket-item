package com.example.websocketitem.service;

import com.example.websocketitem.model.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_message(信息表)】的数据库操作Service
* @createDate 2023-08-30 10:00:31
*/
public interface MessageService extends IService<Message> {

    Result mass(String message);

    Result selectMessage(Long userId, Long fromUserId);

    Result selectMount(Long userId);
}
