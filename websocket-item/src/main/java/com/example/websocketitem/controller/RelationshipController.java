package com.example.websocketitem.controller;

import com.example.websocketitem.model.Relationship;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.service.RelationshipService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relationship")
public class RelationshipController {
    @Resource
    RelationshipService relationshipService;
    /**
     * 根据用户编号获取好友列表
     * */
    @GetMapping("/list/{ownerId}")
    public ResponseMap listRelationship(@PathVariable Long ownerId){
        return relationshipService.listRelationship(ownerId);
    }
    /**
     * 删除好友信息(双向删除
     * */
    @DeleteMapping
    public ResponseMap deleteRelationship(@RequestBody Relationship relationship){
        return relationshipService.deleteRelationship(relationship);
    }
    /**
     * 添加好友信息(双向添加
     * */
    @PostMapping
    public ResponseMap addRelationship(@RequestBody Relationship relationship){
        return relationshipService.addRelationship(relationship);
    }
}
