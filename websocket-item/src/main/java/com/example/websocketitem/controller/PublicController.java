package com.example.websocketitem.controller;

import com.example.websocketitem.model.MasterSlaveRelationship;
import com.example.websocketitem.model.User;
import com.example.websocketitem.service.MasterSlaveService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public")
public class PublicController {
    @Resource
    private MasterSlaveService masterSlaveService;
    @GetMapping
    public Result<List<MasterSlaveRelationship>> listMasterSlave(Long userId){
        return masterSlaveService.listMasterSlaves(userId);
    }
    @PostMapping("addVirtualAccountRelationship")
    public Result addMasterSlave(@RequestBody User user,Long parentId){
        return masterSlaveService.addUserAndMasterSlave(user, parentId);
    }
    @PutMapping("modifyVirtualAccountRelationship")
    public Result updateRelation(@RequestParam Long userId,@RequestParam Long parentId){
        return masterSlaveService.updateRelation(userId,parentId);
    }
    @DeleteMapping("deleteVirtualAccountRelationship")
    public Result deleteRelation(@RequestParam Long userId){
        return masterSlaveService.deleteRelation(userId);
    }
    @DeleteMapping("deleteVirtualAccountRelationshipAndAccount")
    public Result deleteRelationAndUser(@RequestParam Long userId){
        return masterSlaveService.deleteRelationAndUser(userId);
    }
}
