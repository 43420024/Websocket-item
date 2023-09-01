package com.example.websocketitem.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Data;
import com.example.websocketitem.model.User;
import com.example.websocketitem.model.UserInfo;
import com.example.websocketitem.service.UserInfoService;
import com.example.websocketitem.service.UserService;
import com.example.websocketitem.mapper.UserMapper;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author cd
 * @description 针对表【tcd_user(用户表)】的数据库操作Service实现
 * @createDate 2023-08-26 11:05:58
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserInfoService userInfoService;

    @Override
    public Result updateStatus(User user) {
        if (user.getStatus() == 0) {
            user.setStatus(1);
            int update = this.baseMapper.updateById(user);
            return update > 0 ? Result.success("解封成功！") : Result.error("解封失败！");
        }
        user.setStatus(0);
        int updateById = this.baseMapper.updateById(user);
        return updateById > 0 ? Result.success("封号成功！") : Result.error("封号失败！");
    }

    @Override
    public Result selectPage(Integer pageNum, Integer pageSize, Integer gender, Integer phoneNumber, Integer nickname) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //pageNum当前页，pageSize显示条数
        Page<User> page = new Page<>(pageNum, pageSize);
        if (phoneNumber != null) {
            wrapper.like("phone_number", phoneNumber);
        }
        if (nickname != null) {
            wrapper.like("nickname", nickname);
        }
        if (gender != null) {
            wrapper.eq("gender", gender);
        }
        Page<User> userPage = this.baseMapper.selectPage(page, wrapper);
        for (User record : userPage.getRecords()) {
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", record.getId());
            UserInfo userInfo = userInfoService.getOne(queryWrapper);
            record.setUserInfo(userInfo);
        }
        return Result.success(userPage);
    }

    @Override
    public Result updateUser(Data data) {
        int update = this.baseMapper.updateById(data.getUser());
        boolean bool = false;
        if (update > 0) {
            if (data.getUserInfo().getLabelsArray() != null) {
                data.getUserInfo().setLabels(JSON.toJSONString(data.getUserInfo().getLabelsArray()));
                bool = this.userInfoService.updateById(data.getUserInfo());
            }
        }
        return bool ? Result.success("修改成功！") : Result.error("修改失败！");

    }

    @Override
    public Result addUser(User user) {
        user.setCreateTime(new Date());
        final int insert = this.baseMapper.insert(user);
        return insert > 0 ? Result.success() : Result.error();
    }

    @Override
    public Result add(Data data) {
        data.getUser().setCreateTime(new Date());
        data.getUserInfo().setCreateTime(new Date());
        int insert = this.baseMapper.insert(data.getUser());
        boolean save = false;
        if (insert > 0) {
            data.getUserInfo().setUserId(data.getUser().getId());
            data.getUserInfo().setLabels(JSON.toJSONString(data.getUserInfo().getLabelsArray()));
            save = userInfoService.save(data.getUserInfo());
        }
        return save ? Result.success() : Result.error();
    }


//    @Override
//    public Result queryInfo(Long userId) {
//
//        User user = this.baseMapper.selectById(userId);
//        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id", userId);
//        UserInfo userInfo = this.userInfoService.getOne(queryWrapper);
//        user.setUserInfo(userInfo);
//        return Result.success(userInfo);
//    }
}




