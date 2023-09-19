package com.example.websocketitem.core;

import com.alibaba.fastjson.JSON;
import com.example.websocketitem.mapper.MasterSlaveRelationshipMapper;
import com.example.websocketitem.model.Message;
import com.example.websocketitem.model.User;
import com.example.websocketitem.model.UserInfo;
import com.example.websocketitem.service.MessageService;
import com.example.websocketitem.service.UserInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Task {
    @Resource
    private MessageService messageService;
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;
    @Resource
    private UserInfoService userService;
    @Resource
    private MasterSlaveRelationshipMapper masterMapper;

    /**
     * 定时存储到数据库
     */
      //@Scheduled(cron = "0/2 * * * * ?")
    //每隔三天的凌晨4点0分执行
    // @Scheduled(cron = "* * 4 1/3 * ?")
    public void RedisToMysql() {
        List<UserInfo> list = userService.list();
        long resultCount = 0;
        for (UserInfo user : list) {
            BoundListOperations<Object, Object> listOps = redisTemplate.boundListOps("read" + user.getInfoId());
            int size = listOps.size().intValue();
            if (size > 20)
                for (int i = 0; i < size - 20; i++) {
                    Object o = listOps.leftPop();
                    final Message message1 = JSON.parseObject(JSON.toJSONString(o), Message.class);
                    messageService.save(message1);
                    // 写入成功
                    resultCount++;
                }
        }
        log.info(resultCount + "条聊天记录，已写入数据库");
    }
}
