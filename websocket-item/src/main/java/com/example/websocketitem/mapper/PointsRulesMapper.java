package com.example.websocketitem.mapper;

import com.example.websocketitem.model.PointsRules;

import java.util.List;

/**
* @author ASUS
* @description 针对表【tcd_points_rules(积分规则表)】的数据库操作Mapper
* @createDate 2023-09-05 11:27:34
* @Entity com.example.websocketitem.model.PointsRules
*/
public interface PointsRulesMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PointsRules record);

    int insertSelective(PointsRules record);

    PointsRules selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PointsRules record);

    int updateByPrimaryKey(PointsRules record);

    List<PointsRules> selectAll();

}
