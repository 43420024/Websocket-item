package com.example.websocketitem.controller;

import com.example.websocketitem.model.Data;
import com.example.websocketitem.model.User;
import com.example.websocketitem.model.UserInfo;
import com.example.websocketitem.service.UserInfoService;
import com.example.websocketitem.service.UserService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 封号 解冻
     *
     * @param user
     * @return
     */
    @PutMapping("/updateStatus")
    public Result updateStatus(@RequestBody User user) {
        return userService.updateStatus(user);
    }

    /**
     * 用户信息遍历
     *
     * @param pageNum
     * @param pageSize
     * @param gender
     * @param phoneNumber
     * @param nickname
     * @return
     */
    @GetMapping("/list")
    public Result list(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer gender,
            @RequestParam(required = false) Integer phoneNumber,
            @RequestParam(required = false) Integer nickname
    ) {
        return userService.selectPage(pageNum, pageSize, gender, phoneNumber, nickname);
    }

    /**
     * 根据用户id查用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/query/{userId}")
    public Result query(@PathVariable Long userId) {
        return userInfoService.queryInfo(userId);
    }


    /**
     * 修改用户消息
     *
     * @param data
     * @return
     */
    @Transactional
    @PutMapping("/update")
    public Result update(@RequestBody Data data) {
        return userService.updateUser(data);
    }

    /**
     * 修改用户基本消息
     *
     * @param userInfo
     * @return
     */
    @PutMapping("/updateInfo")
    public Result updateInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.updateInfo(userInfo);
    }


    /**
     * 添加基本消息
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/addInfo")
    public Result addInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.addInfo(userInfo);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * 添加用户及基本消息
     *
     * @param data
     * @return
     */
    @Transactional
    @PostMapping("/add")
    public Result add(@RequestBody Data data) {
        return userService.add(data);
    }


}
