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
    ResponseMap addRelationship(Relationship relationship);

    ResponseMap deleteRelationship(Relationship relationship);

    ResponseMap listRelationship(Long ownerId);
}
