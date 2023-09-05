package com.example.websocketitem.service;

import com.example.websocketitem.model.Relationship;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.model.ResponseMap;

/**
* @author cd
* @description 针对表【tcd_relationship(好友关系表)】的数据库操作Service
* @createDate 2023-08-29 16:18:39
*/
public interface RelationshipService extends IService<Relationship> {
    /**
     * 添加好友信息(双向添加
     * */
    ResponseMap addRelationship(Relationship relationship);
    /**
     * 删除好友信息(双向删除
     * */
    ResponseMap deleteRelationship(Relationship relationship);
    /**
     * 根据用户编号获取好友列表
     * */
    ResponseMap listRelationship(Long ownerId);
}
