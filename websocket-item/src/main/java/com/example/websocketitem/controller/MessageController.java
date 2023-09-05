package com.example.websocketitem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.websocketitem.core.WebSocketServer;
import com.example.websocketitem.model.Data;
import com.example.websocketitem.model.Message;
import com.example.websocketitem.model.User;
import com.example.websocketitem.service.MessageService;
import com.example.websocketitem.service.UserService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("messages")
public class MessageController {



    @Resource
    private MessageService messageService;

    /**
     * 群发
     *
     * @param message
     * @return
     */
    @GetMapping("/test")
    public Result sendAll(@RequestParam("message") String message) {
        return messageService.mass(message);
    }

    /**
     * 查询消息
     * @param userId
     * @param fromUserId
     * @return
     */
    @GetMapping("/{userId}")
    public Result read(@PathVariable Long userId, @RequestParam Long fromUserId) {
        return messageService.selectMessage(userId,fromUserId);
    }

    /**
     * 给我的消息数量
     *
     * @param userId
     * @return
     */
    @GetMapping("/query/{userId}")
    public Result query(@PathVariable Long userId) {

      return   messageService.selectMount(userId);
    }

}
