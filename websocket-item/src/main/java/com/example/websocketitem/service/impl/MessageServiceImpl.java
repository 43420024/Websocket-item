package com.example.websocketitem.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.core.WebSocketServer;
import com.example.websocketitem.mapper.UserInfoMapper;
import com.example.websocketitem.model.Data;
import com.example.websocketitem.model.Message;
import com.example.websocketitem.model.UserInfo;
import com.example.websocketitem.service.MessageService;
import com.example.websocketitem.mapper.MessageMapper;
import com.example.websocketitem.utils.Result;
import com.example.websocketitem.vo.UserInfoVO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author cd
* @description 针对表【tcd_message(信息表)】的数据库操作Service实现
* @createDate 2023-08-30 10:00:31
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;
    @Resource
    private WebSocketServer webSocketServer;
//    @Resource
//    private UserService userService;
    @Resource
    private UserInfoMapper userInfoMapper;


    @Override
    public Result mass(String message) {
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
        return Result.success("发送成功");

    }

    @Override
    public Result selectMessage(Long userId, Long fromUserId) {
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
        Collections.sort(messageList,(Comparator.comparing(Message::getCreateTime)));

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

    @Override
    public Result selectMount(Long userId) {
        BoundListOperations<Object, Object> myUnread = redisTemplate.boundListOps("unread" + userId);

        final List<Object> range = myUnread.range(0, myUnread.size());
        List<Message> myReadMessage = JSONObject.parseArray(JSON.toJSONString(range), Message.class);
        //final Map<Long, Integer> collect = myReadMessage.stream().collect(Collectors.toMap(Message::getComeFrom, el -> 1, Integer::sum));
        final Map<Long, Long> collect = myReadMessage.stream().collect(Collectors.groupingBy(Message::getComeFrom, Collectors.counting()));

        List<UserInfo> userList = new ArrayList<>();
        for (Map.Entry<Long, Long> longLongEntry : collect.entrySet()) {
            final UserInfo userInfo = userInfoMapper.selectById(longLongEntry.getKey());
            userInfo.setUnreadMessageNumber(longLongEntry.getValue().intValue());
            userList.add(userInfo);
            System.out.println("key:" + longLongEntry.getKey() + ",value:" + longLongEntry.getValue());
        }

        return Result.success(userList);
    }

    @Override
    public Result queryChatListUser(Long userId) {
        // 查数据库聊天列表
        List<Long> comFormList = this.baseMapper.selectDistinctMessages(userId);
        // TODO: 2023/9/20 去查redis聊天用户列表
        BoundListOperations<Object, Object> myUnread = redisTemplate.boundListOps("unread" + userId);
        BoundListOperations<Object, Object> myRead = redisTemplate.boundListOps("read" + userId);
        //未读
        List<Object> object = myUnread.range(0, myUnread.size());
        List<Message> unReadMessage = JSONObject.parseArray(JSON.toJSONString(object), Message.class);
        //已读
        List<Object> objects = myRead.range(0, myRead.size());
        List<Message> readMessage = JSONObject.parseArray(JSON.toJSONString(objects), Message.class);
        List<Message> messageList = new ArrayList();
        messageList.addAll(unReadMessage);
        messageList.addAll(readMessage);

        List<Message> arrays = messageList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(Message :: getComeFrom))), ArrayList::new));

        for (Message array : arrays) {
            Long comeFrom = array.getComeFrom();
            if(!ObjectUtil.contains(comFormList,comeFrom)){
                comFormList.add(comeFrom);
            }

        }
        // 返回数据给前端
        List<UserInfoVO> userInfoVOList = new ArrayList<>();
        for (Long comFormId:comFormList) {
            UserInfoVO userInfoVO = userInfoMapper.selectNicknameAndHeadPathByUserId(comFormId);
            userInfoVO.setUserId(comFormId);
            userInfoVOList.add(userInfoVO);
        }
        return Result.ok("查询成功",userInfoVOList);
    }

    @Override
    public Result chatHistoryBetweenTwoPeople(String current, String opposite) {

         long userId = Long.valueOf(current).longValue();
         long anotherId = Long.valueOf(opposite).longValue();
        BoundListOperations<Object, Object> myUnread = redisTemplate.boundListOps("unread" + userId);
        BoundListOperations<Object, Object> myRead = redisTemplate.boundListOps("read" + userId);
        BoundListOperations<Object, Object> otherUnread = redisTemplate.boundListOps("unread" + anotherId);
        BoundListOperations<Object, Object> otherRead = redisTemplate.boundListOps("read" + anotherId);
        //发给我的未读消息处理
        List<Object> range = myUnread.range(0, myUnread.size());
        List<Message> messages = JSONObject.parseArray(JSON.toJSONString(range), Message.class);
        for (Message message : messages) {
            if (message.getComeFrom() == anotherId) {
                myRead.rightPush(message);
                myUnread.remove(1, message);
            }
        }

        //发给我的已读消息
        List<Object> objects = myRead.range(0, myRead.size());
        List<Message> myReadMessage = JSONObject.parseArray(JSON.toJSONString(objects), Message.class);
        List<Message> collect = myReadMessage.stream()
                .filter((Message me) -> me.getComeFrom() == anotherId).collect(Collectors.toList());

        //我发给他的已读消息
        List<Object> objectList = otherRead.range(0, otherRead.size());
        List<Message> otherReadMessage = JSONObject.parseArray(JSON.toJSONString(objectList), Message.class);
        List<Message> collects = otherReadMessage.stream()
                .filter((Message m) -> m.getComeFrom() == userId).collect(Collectors.toList());

        //我发给对方未读消息
        List<Object> object = otherUnread.range(0, otherUnread.size());
        List<Message> otherUnReadMessage = JSONObject.parseArray(JSON.toJSONString(object), Message.class);
        List<Message> collectList = otherUnReadMessage.stream()
                .filter((Message m) -> m.getComeFrom() == userId).collect(Collectors.toList());

        //所有消息
        List<Message> messageList = new ArrayList();
        messageList.addAll(collects);
        messageList.addAll(collect);
        messageList.addAll(collectList);

        //排序
        Collections.sort(messageList,(Comparator.comparing(Message::getCreateTime)));

        for (Message message : messageList) {
            cn.hutool.json.JSONObject jsonObject = new cn.hutool.json.JSONObject();
            jsonObject.set("from", message.getComeFrom());
            jsonObject.set("to", message.getFromTo());
            jsonObject.set("text",message.getMessage());
            webSocketServer.historicalChatInformation(current, String.valueOf(jsonObject));
        }
        return null;
    }
}




