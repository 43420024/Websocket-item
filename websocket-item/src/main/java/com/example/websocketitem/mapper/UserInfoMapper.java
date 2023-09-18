package com.example.websocketitem.mapper;
import java.util.List;

import com.example.websocketitem.vo.UserInfoVO;
import org.apache.ibatis.annotations.Param;

import com.example.websocketitem.model.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author cd
* @description 针对表【tcd_user_info】的数据库操作Mapper
* @createDate 2023-08-26 11:05:58
* @Entity com.example.websocketitem.model.UserInfo
*/
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    UserInfoVO selectNicknameAndHeadPathByUserId(@Param("userId") Long userId);
}




