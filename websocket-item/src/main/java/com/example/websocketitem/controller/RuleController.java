package com.example.websocketitem.controller;


import com.example.websocketitem.model.Member;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.Rule;
import com.example.websocketitem.service.MemberService;
import com.example.websocketitem.service.RuleService;
import com.example.websocketitem.utils.DataType;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Rule")
@Log4j
public class RuleController {
    @Resource
    private RuleService ruleService;

    //查询单个会员规则表
    @GetMapping("{id}")
    public DataType selectRule(@PathVariable Integer id){
        return ruleService.selectOneRule(id);
    }

    //删除单个会员规则表
    @DeleteMapping("{id}")
    public DataType deleteRule(@PathVariable Integer id){
        return ruleService.deleteRule(id);
    }

    //添加和修改会员规则表
    @PostMapping("addAndEdit")
    public DataType addOrEdit(@RequestBody Rule rule){
        System.out.println(rule.toString());
        return ruleService.addOrEditOne(rule);
    }




}
