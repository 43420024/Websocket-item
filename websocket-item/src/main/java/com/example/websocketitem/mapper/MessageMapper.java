package com.example.websocketitem.mapper;

import com.example.websocketitem.model.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author cd
* @description 针对表【tcd_message(信息表)】的数据库操作Mapper
* @createDate 2023-08-30 10:00:31
* @Entity com.example.websocketitem.model.Message
*/
public interface MessageMapper extends BaseMapper<Message> {
    @Select("SELECT DISTINCT `come_from` FROM `tcd_message` WHERE from_to = #{userId}")
    List<Long> selectDistinctMessages(@Param("userId") Long userId);
}




