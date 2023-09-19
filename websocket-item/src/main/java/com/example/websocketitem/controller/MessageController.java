package com.example.websocketitem.controller;

import com.example.websocketitem.service.MessageService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("messages")
@CrossOrigin
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
    @GetMapping("/sendChatHistory")
    public Result sendChatHistory(@RequestParam("current") String current,@RequestParam("opposite") String opposite) {
        return messageService.chatHistoryBetweenTwoPeople(current,opposite);
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

    @GetMapping("/queryChatListUser/{userId}")
    public Result queryChatListUser(@PathVariable Long userId) {
        return   messageService.queryChatListUser(userId);
    }

}
