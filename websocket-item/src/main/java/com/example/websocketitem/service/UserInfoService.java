package com.example.websocketitem.service;

import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_user_info】的数据库操作Service
* @createDate 2023-08-26 11:05:58
*/
public interface UserInfoService extends IService<UserInfo> {

    Result addInfo(UserInfo userInfo);

    Result queryInfo(Long userId);

    Result updateInfo(UserInfo userInfo);

    UserInfo getInfo(Long userId);
    /**
     * 获取未处理举报信息用户编号及该用户未处理举报信息个数
     */
    ResponseMap getReportUserInfo();
    /**
     * 根据模糊查询昵称获取未处理举报信息用户编号及该用户未处理举报信息个数
     */
    ResponseMap getSearchReportUserInfo(String value);
    /**
     * 获取相册未审核用户编号及未审核相册个数
     * */
    ResponseMap getAlbumUserInfo();
    /**
     * 根据模糊查询昵称获取相册未审核用户编号及未审核相册个数
     */
    ResponseMap getSearchAlbumUserInfo(String value);

}
