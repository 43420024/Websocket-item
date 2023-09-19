package com.example.websocketitem.controller;

import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.UserInfo;
import com.example.websocketitem.service.UserInfoService;
import com.example.websocketitem.utils.DataType;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
//    @Resource
//    private UserService userService;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 封号 解冻
     *
     * @param userInfo
     * @return
     */
    @PutMapping("/updateStatus")
    public Result updateStatus(@RequestBody UserInfo userInfo) {
        return userInfoService.updateStatus(userInfo);
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
        return userInfoService.selectPage(pageNum, pageSize, gender, phoneNumber, nickname);
    }




    @GetMapping("/getOneUserAndInfo/{userId}")
    public DataType getUserAndInfo(@PathVariable Long userId){
        return userInfoService.queryUserInfo(userId);
    }



    /**
     * 获取未处理举报信息用户编号及该用户未处理举报信息个数
     */
    @GetMapping("/reportStat")
    public ResponseMap getReportUserInfo(){
        return userInfoService.getReportUserInfo();
    }
    /**
     * 根据模糊查询昵称获取未处理举报信息用户编号及该用户未处理举报信息个数
     */
    @GetMapping("/reportStat/search")
    public ResponseMap getSearchReportUserInfo(@RequestParam String value){
        return userInfoService.getSearchReportUserInfo(value);
    }
    /**
     * 获取相册未审核用户编号及未审核相册个数
     * */
    @GetMapping("/albumStat")
    public ResponseMap getAlbumUserInfo(){
        return userInfoService.getAlbumUserInfo();
    }
    /**
     * 根据模糊查询昵称获取相册未审核用户编号及未审核相册个数
     */
    @GetMapping("/albumStat/search")
    public ResponseMap getSearchAlbumUserInfo(@RequestParam String value){
        return userInfoService.getSearchAlbumUserInfo(value);
    }
}
