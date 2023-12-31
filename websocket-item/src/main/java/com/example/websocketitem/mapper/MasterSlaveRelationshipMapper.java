package com.example.websocketitem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.websocketitem.model.MasterSlaveRelationship;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author ASUS
* @description 针对表【tcd_master_slave_relationship(主从账号表)】的数据库操作Mapper
* @createDate 2023-08-30 10:19:11
* @Entity com.example.websocketitem.model.MasterSlaveRelationship
*/
public interface MasterSlaveRelationshipMapper {

    int deleteByPrimaryKey(Long id);

    int insert(MasterSlaveRelationship record);

    int insertSelective(MasterSlaveRelationship record);

    MasterSlaveRelationship selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MasterSlaveRelationship record);

    int updateByPrimaryKey(MasterSlaveRelationship record);

    /**
     * 根据用户id找到所属主账号的用户id
     * @param userId 提供的用户id
     * @return 主账号用户id值
     */
    @Select("select parent_id from `tcd_master_slave_relationship` where user_id = #{userId}")
    Long selectParentIdByUserId(@Param("userId") Long userId);

    List<MasterSlaveRelationship> listMasterSlaves(Long parentId);

    int updateParentIdByUserId(@Param("parentId") Long parentId, @Param("userId") Long userId);

    int deleteByUserId(@Param("userId") Long userId);
}
