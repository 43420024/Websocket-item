package com.example.websocketitem.controller;

import com.example.websocketitem.domain.Data;
import com.example.websocketitem.domain.User;
import com.example.websocketitem.domain.UserInfo;
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
     * @param user
     * @return
     */
    @PutMapping("/updateStatus")
    public Result updateStatus(@RequestBody User user) {
        return userService.updateStatus(user);
    }

    /**
     * 用户信息遍历
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
    ){
        return userService.selectPage(pageNum,pageSize,gender,phoneNumber,nickname);
    }

    @Transactional
    @PutMapping("/update")
    public Result update(@RequestBody Data data){
        return userService.updateUser(data);
    }




    @PostMapping("/add")
    public Result add(@RequestBody UserInfo userInfo){
        return userInfoService.add(userInfo);
    }

}
