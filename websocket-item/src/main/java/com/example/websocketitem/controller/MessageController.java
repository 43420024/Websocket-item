package com.example.websocketitem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.websocketitem.core.WebSocketServer;
import com.example.websocketitem.model.Data;
import com.example.websocketitem.model.Message;
import com.example.websocketitem.model.User;
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
    private RedisTemplate<Object, Object> redisTemplate;
    @Resource
    private WebSocketServer webSocketServer;
    @Resource
    private UserService userService;

    @GetMapping("/test")
    public Result sendAll(@RequestParam("message") String message){
        BoundListOperations<Object, Object> userTest = redisTemplate.boundListOps("messages" );
         Message messages = new Message();
         messages.setMessage(message);
         //发给所有人
         messages.setFromTo(0L);
         //来自系统账号
         messages.setComeFrom(0L);
         messages.setSender(0L);
         //角色系统
         messages.setRole(3);
         messages.setType(1);
         messages.setCreateTime(new Date());
        userTest.rightPush(message);
        webSocketServer.sendAllMessage(message);
        return Result.success("推送成功");
    }

    @GetMapping("/{userId}")
    public Result read(@PathVariable Long userId, @RequestParam Long fromUserId) {
        BoundListOperations<Object, Object> myUnread = redisTemplate.boundListOps("unread" + userId);
        BoundListOperations<Object, Object> myRead = redisTemplate.boundListOps("read" + userId);
        BoundListOperations<Object, Object> otherUnread = redisTemplate.boundListOps("unread" + fromUserId);
        BoundListOperations<Object, Object> otherRead = redisTemplate.boundListOps("read" + fromUserId);

        //发给我的未读消息处理
        List<Object> range = myUnread.range(0, myUnread.size());
        List<Message> messages = JSONObject.parseArray(JSON.toJSONString(range), Message.class);
        for (Message message : messages) {
            if (message.getComeFrom() == fromUserId) {
                myRead.rightPush(message);
                myUnread.remove(1, message);
            }
        }

        //发给我的已读消息
        List<Object> objects = myRead.range(0, myRead.size());
        List<Message> myReadMessage = JSONObject.parseArray(JSON.toJSONString(objects), Message.class);
        List<Message> collect = myReadMessage.stream()
                .filter((Message me) -> me.getComeFrom() == fromUserId).collect(Collectors.toList());

        //我发给他的已读消息
        List<Object> objectList = otherRead.range(0, otherRead.size());
        List<Message> otherReadMessage = JSONObject.parseArray(JSON.toJSONString(objectList), Message.class);
        List<Message> collects = otherReadMessage.stream()
                .filter((Message m) -> m.getComeFrom() == userId).collect(Collectors.toList());

        //已读消息
        List<Message> messageList = new ArrayList();
        messageList.addAll(collects);
        messageList.addAll(collect);

        //排序
        Collections.sort(messageList, (o1, o2) -> o1.getCreateTime().compareTo(o1.getCreateTime()));

        //我发给对方未读消息
        List<Object> object = otherUnread.range(0, otherUnread.size());
        List<Message> otherUnReadMessage = JSONObject.parseArray(JSON.toJSONString(object), Message.class);
        List<Message> collectList = otherUnReadMessage.stream()
                .filter((Message m) -> m.getComeFrom() == userId).collect(Collectors.toList());

        Data data = new Data();
        data.setMessageList(messageList);
        data.setCollectList(collectList);

        return Result.success(data);
    }

    /**
     * 给我的消息数量
     * @param userId
     * @return
     */
    @GetMapping("/query/{userId}")
    public Result query(@PathVariable String userId) {

        BoundListOperations<Object, Object> myUnread = redisTemplate.boundListOps("unread" + userId);

        final List<Object> range = myUnread.range(0, myUnread.size());
        List<Message> myReadMessage = JSONObject.parseArray(JSON.toJSONString(range), Message.class);
        //final Map<Long, Integer> collect = myReadMessage.stream().collect(Collectors.toMap(Message::getComeFrom, el -> 1, Integer::sum));
        final Map<Long, Long> collect = myReadMessage.stream().collect(Collectors.groupingBy(Message::getComeFrom, Collectors.counting()));

        List<User> userList = new ArrayList<>();
        for (Map.Entry<Long, Long> longLongEntry : collect.entrySet()) {
            final User user = userService.getById(longLongEntry.getKey());
            user.setUnreadMessageNumber(longLongEntry.getValue().intValue());
            userList.add(user);
            System.out.println("key:"+longLongEntry.getKey()+",value:"+longLongEntry.getValue());
        }

        return Result.success(userList);
    }

}
