package com.example.websocketitem.mapper;
import java.util.List;

import com.example.websocketitem.model.Points;

/**
* @author ASUS
* @description 针对表【tcd_points(积分表)】的数据库操作Mapper
* @createDate 2023-08-26 11:07:03
* @Entity com.example.websocketitem.model.Points
*/
public interface PointsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Points record);

    int insertSelective(Points record);

    Points selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Points record);

    int updateByPrimaryKey(Points record);

    List<Points> selectAll();
}
