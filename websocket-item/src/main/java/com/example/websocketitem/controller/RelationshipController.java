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

    @GetMapping("/list/{ownerId}")
    public ResponseMap listRelationship(@PathVariable Long ownerId){
        return relationshipService.listRelationship(ownerId);
    }

    @DeleteMapping
    public ResponseMap deleteRelationship(@RequestBody Relationship relationship){
        return relationshipService.deleteRelationship(relationship);
    }

    @PostMapping
    public ResponseMap addRelationship(@RequestBody Relationship relationship){
        return relationshipService.addRelationship(relationship);
    }
}
