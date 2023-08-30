package com.example.websocketitem.core;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.websocketitem.model.Message;
import com.example.websocketitem.service.MasterSlaveService;
import com.example.websocketitem.utils.ApplicationContextRegister;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ServerEndpoint(value = "/chongdong/{username}")
@Component
public class WebSocketServer {
    /**
     * 记录当前在线连接数
     */
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
//    private static StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    public void setStringRedisTemplate(StringRedisTemplate template) {
//        WebSocketServer.stringRedisTemplate = template;
//    }


    private static RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        WebSocketServer.redisTemplate = redisTemplate;
    }


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        username = StrUtil.trim(username);
        sessionMap.put(username, session);
        log.info("有新用户加入，username={}, 当前在线人数为：{}", username, sessionMap.size());
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.set("users", array);
        for (Object key : sessionMap.keySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("username", key);
            // {"username": "zhang", "username": "admin"}
            array.add(jsonObject);
        }
//        {"users": [{"username": "zhang"},{ "username": "admin"}]}
        sendAllMessage(JSONUtil.toJsonStr(result));  // 后台发送消息给所有的客户端
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        username = StrUtil.trim(username);
        sessionMap.remove(username);
        log.info("有一连接关闭，移除username={}的用户session, 当前在线人数为：{}", username, sessionMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * 后台收到客户端发送过来的消息
     * onMessage 是一个消息的中转站
     * 接受 浏览器端 socket.send 发送过来的 json数据
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) throws JsonProcessingException {
         Message messages = new Message();
         Long from = Long.valueOf(username);
        username = StrUtil.trim(username);
        log.info("服务端收到用户username={}的消息:{}", username, message);
        JSONObject obj = JSONUtil.parseObj(message);
        String toUsername = obj.getStr("to"); // to表示发送给哪个用户，比如 admin
        String text = obj.getStr("text"); // 发送的消息文本  hello
        String video = obj.getStr("video"); // 发送的视频信息
        String audio = obj.getStr("audio"); // 发送的音频信息
        String image = obj.getStr("image"); // 发送的图片信息
        String emo = obj.getStr("emo"); // 发送的表情信息
        String fromUsername = obj.getStr("from"); // from表示消息来自哪。
        // {"to": "admin", "text": "聊天文本"}
        Long userId = Long.valueOf(toUsername);
        MasterSlaveService masterSlaveService = ApplicationContextRegister.getApplicationContext().getBean(MasterSlaveService.class);
        Long parentId = masterSlaveService.getParentId(userId);
        if(ObjectUtil.isNotNull(parentId)){ // 当该用户的父级id（主号id）不为空时，就将别人发过来的消息一块转发给父级id账号
            String parentToUsername = parentId.toString();
            Session parentToSession = sessionMap.get(parentToUsername);
            sendOneMessage(username,parentToSession,text,image,video,emo,audio,parentToUsername);

            messages.setSender(parentId);
            messages.setRole(2);
        }else {
            messages.setRole(1);
        }
        if(ObjectUtil.isNotEmpty(fromUsername)){ // 当from字段不为空时（主号id通过from字段直接指定是哪个虚拟账号（10个账号中的）发给用户的），
            Session toSession = sessionMap.get(toUsername); // 根据 to用户名来获取 session，再通过session发送消息文本
            sendOneMessage(fromUsername, toSession, text, image, video, emo, audio, toUsername);
        }else {
            // 当发的from为空，即为一般的用户发送的消息传给服务器，服务器再转发给虚拟10个账号中具体的账号
            Session toSession = sessionMap.get(toUsername); // 根据 to用户名来获取 session，再通过session发送消息文本
            sendOneMessage(username, toSession, text, image, video, emo, audio, toUsername);
        }
        // TODO 聊天数据持久化存储
        obj.set("from", username);
        messages.setTo(userId);
        messages.setFrom(from);
        messages.setCreateTime(new Date());
        if (ObjectUtil.isNotEmpty(text)) {
            messages.setMessage(text);
             messages.setType(1);
        }
        if (ObjectUtil.isNotEmpty(image)){
            messages.setMessage(image);
            messages.setType(2);
        }
        if (ObjectUtil.isNotEmpty(video)){
            messages.setMessage(video);
            messages.setType(3);
        }
        if (ObjectUtil.isNotEmpty(emo)){
            messages.setMessage(emo);
            messages.setType(4);
        }
        if (ObjectUtil.isNotEmpty(audio)){
            messages.setMessage(audio);
            messages.setType(5);
        }

        BoundListOperations<Object, Object> userTest = redisTemplate.boundListOps("dd"+userId);
        userTest.rightPush(messages);
        final List<Object> range = userTest.range(0, userTest.size());
        for (Object o : range) {
            System.out.println(o);
        }
        // 插入一条string数据类型
//        stringRedisTemplate.opsForList().leftPush("chat", obj.toString());
//        // 读取一条string数据类型
//        Object name = stringRedisTemplate.opsForList().range("chat", 0, -1);
//        log.info("存入redis消息 {}",name);
    }

    private void sendOneMessage(String username, Session toSession, String text, String image, String video, String emo, String audio, String toUsername) {
        if (toSession != null) {
            // 服务器端 再把消息组装一下，组装后的消息包含发送人和发送的文本内容
            // {"from": "zhang", "text": "hello"}

            JSONObject jsonObject = new JSONObject();
            jsonObject.set("from", username);  // from 是 zhang
            // 判断消息类型并组装好后转发给别人
            if (ObjectUtil.isNotEmpty(text)) {
                jsonObject.set("text", text);  // text 同上面的text
            }
            if (ObjectUtil.isNotEmpty(image)){
                jsonObject.set("image", image);
            }
            if (ObjectUtil.isNotEmpty(video)){
                jsonObject.set("video", video);
            }
            if (ObjectUtil.isNotEmpty(emo)){
                jsonObject.set("emo", emo);
            }
            if (ObjectUtil.isNotEmpty(audio)){
                jsonObject.set("audio", audio);
            }
            this.sendMessage(jsonObject.toString(), toSession);
            log.info("发送给用户username={}，消息：{}", toUsername, jsonObject.toString());
        } else {
            log.info("发送失败，未找到用户username={}的session", toUsername);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }

    /**
     * 服务端发送消息给所有客户端
     */
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败", e);
        }
    }
}