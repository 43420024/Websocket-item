package com.example.websocketitem.service;

import com.example.websocketitem.model.Rule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.DataType;

/**
* @author cd
* @description 针对表【tcd_rule(会员规则表)】的数据库操作Service
* @createDate 2023-09-01 09:49:56
*/
public interface RuleService extends IService<Rule> {

    DataType selectOneRule(Integer id);

    DataType deleteRule(Integer id);

    DataType addOrEditOne(Rule rule);
}
