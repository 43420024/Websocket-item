package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Rule;
import com.example.websocketitem.service.RuleService;
import com.example.websocketitem.mapper.RuleMapper;
import org.springframework.stereotype.Service;

/**
* @author cd
* @description 针对表【tcd_rule(会员规则表)】的数据库操作Service实现
* @createDate 2023-09-01 09:49:56
*/
@Service
public class RuleServiceImpl extends ServiceImpl<RuleMapper, Rule>
    implements RuleService{

}




