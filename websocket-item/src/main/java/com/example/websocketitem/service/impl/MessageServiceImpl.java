package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Message;
import com.example.websocketitem.service.MessageService;
import com.example.websocketitem.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_message(信息表)】的数据库操作Service实现
* @createDate 2023-08-30 10:00:31
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

}




