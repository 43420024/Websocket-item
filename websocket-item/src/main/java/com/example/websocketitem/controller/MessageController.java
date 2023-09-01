package com.example.websocketitem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.websocketitem.model.Data;
import com.example.websocketitem.model.Message;
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

    @GetMapping("/{userId}")
    public Result read(@PathVariable Long userId, @RequestParam Long from) {
        BoundListOperations<Object, Object> myUnread = redisTemplate.boundListOps("unread" + userId);
        BoundListOperations<Object, Object> myRead = redisTemplate.boundListOps("read" + userId);
        BoundListOperations<Object, Object> otherUnread = redisTemplate.boundListOps("unread" + from);
        BoundListOperations<Object, Object> otherRead = redisTemplate.boundListOps("read" + from);

        //发给我的未读消息处理
        List<Object> range = myUnread.range(0, myUnread.size());
        List<Message> messages = JSONObject.parseArray(JSON.toJSONString(range), Message.class);
        for (Message message : messages) {
            if (message.getFrom() == from) {
                myRead.rightPush(message);
                myUnread.remove(1, message);
            }
        }

        //发给我的已读消息
        List<Object> objects = myRead.range(0, myRead.size());
        List<Message> myReadMessage = JSONObject.parseArray(JSON.toJSONString(objects), Message.class);
        List<Message> collect = myReadMessage.stream()
                .filter((Message me) -> me.getFrom() == from).collect(Collectors.toList());

        //我发给他的已读消息
        List<Object> objectList = otherRead.range(0, otherRead.size());
        List<Message> otherReadMessage = JSONObject.parseArray(JSON.toJSONString(objectList), Message.class);
        List<Message> collects = otherReadMessage.stream()
                .filter((Message m) -> m.getFrom() == userId).collect(Collectors.toList());

        //已读消息
        List<Message> messageList = new ArrayList();
        messageList.addAll(collects);
        messageList.addAll(collect);

        //排序
        Collections.sort(messageList, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getCreateTime().compareTo(o1.getCreateTime());
            }
        });

        //我发给对方未读消息
        List<Object> object = otherUnread.range(0, otherUnread.size());
        List<Message> otherUnReadMessage = JSONObject.parseArray(JSON.toJSONString(object), Message.class);
        List<Message> collectList = otherUnReadMessage.stream()
                .filter((Message m) -> m.getFrom() == userId).collect(Collectors.toList());

        Data data = new Data();
        data.setMessageList(messageList);
        data.setCollectList(collectList);

        return Result.success(data);
    }

}
