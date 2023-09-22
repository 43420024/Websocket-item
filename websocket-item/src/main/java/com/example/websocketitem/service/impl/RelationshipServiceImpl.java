package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.factory.EntityFactory;
import com.example.websocketitem.model.Relationship;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.service.RelationshipService;
import com.example.websocketitem.mapper.RelationshipMapper;
import com.example.websocketitem.utils.ResponseMapUtil;
import com.example.websocketitem.utils.WrapperUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author cd
* @description 针对表【tcd_relationship(好友关系表)】的数据库操作Service实现
* @createDate 2023-08-29 16:18:39
*/
@Service
public class RelationshipServiceImpl extends ServiceImpl<RelationshipMapper, Relationship>
    implements RelationshipService{
    @Resource
    ResponseMapUtil<Relationship> responseMapUtil;
    @Resource
    WrapperUtil<Relationship> wrapperUtil;

    Relationship anotherRelationship = EntityFactory.createRelationship();
    /**
     * 添加好友信息(双向添加
     * */
    @Override
    public ResponseMap addRelationship(Relationship relationship) {
        relationship.setCreateTime(new Date());
        anotherRelationship.setOwnerId(relationship.getFriendId());
        anotherRelationship.setFriendId(relationship.getOwnerId());
        anotherRelationship.setCreateTime(relationship.getCreateTime());
        return responseMapUtil.addEntity(this.save(relationship) && this.save(anotherRelationship));
    }
    /**
     * 删除好友信息(双向删除
     * */
    @Override
    public ResponseMap deleteRelationship(Relationship relationship) {
        anotherRelationship.setOwnerId(relationship.getFriendId());
        anotherRelationship.setFriendId(relationship.getOwnerId());
        return responseMapUtil.deleteEntity(this.removeById(this.getOne(wrapperUtil.getRelationship(relationship)).getId())
        && this.removeById(this.getOne(wrapperUtil.getRelationship(anotherRelationship)).getId()));
    }
    /**
     * 根据用户编号获取好友列表
     * */
    @Override
    public ResponseMap listRelationship(Long ownerId) {
        return responseMapUtil.getList(this.list(wrapperUtil.listRelationship(ownerId)));
    }

    @Override
    public ResponseMap userLogOff(Long ownerId) {
        List<Relationship> list = this.list(wrapperUtil.wrapperAllRelationship(ownerId));
        return responseMapUtil.deleteEntity(this.removeByIds(list));
    }
}




