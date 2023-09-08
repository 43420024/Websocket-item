package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.factory.DateTypeFactory;
import com.example.websocketitem.mapper.MemberMapper;
import com.example.websocketitem.model.Member;
import com.example.websocketitem.model.Rule;
import com.example.websocketitem.service.RuleService;
import com.example.websocketitem.mapper.RuleMapper;
import com.example.websocketitem.utils.DataType;
import com.example.websocketitem.utils.PageUtil;
import com.example.websocketitem.utils.ResponseMapUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.Date;

/**
* @author cd
* @description 针对表【tcd_rule(会员规则表)】的数据库操作Service实现
* @createDate 2023-09-01 09:49:56
*/
@Service
public class RuleServiceImpl extends ServiceImpl<RuleMapper, Rule>
    implements RuleService{
    @Resource
    RuleMapper ruleMapper;
    DataType dataType= DateTypeFactory.create();
    @Resource
    ResponseMapUtil<Rule> responseMapUtil;
    @Resource
    PageUtil<Rule> pageUtil;

    @Override
    public DataType selectOneRule(Integer id) {
        if (id!=null){
            QueryWrapper<Rule> wrapper = new QueryWrapper<>();
            Rule rule=ruleMapper.selectOne(wrapper.eq("id",id));
            if (rule!=null){
                dataType.setData(rule);
                dataType.setFlag(true);
                dataType.setMessage("查询成功");
            }else {
                dataType.setMessage("查询失败，不存在该信息，请确认后重试！！！");
                dataType.setFlag(false);
                dataType.setData(null);
            }
        }else {
            dataType.setData(null);
            dataType.setFlag(false);
            dataType.setMessage("参数错误，请确认后重试！！！");
        }
        return dataType;
    }

    @Override
    public DataType deleteRule(Integer id) {
        if (id!=null){
            if(ruleMapper.deleteById(id)>0){
                dataType.setData(true);
                dataType.setFlag(true);
                dataType.setMessage("删除成功");
            }else {
                dataType.setData(false);
                dataType.setFlag(false);
                dataType.setMessage("删除失败，没有该信息，请确认后重试！！！");
            }
        }else {
            dataType.setData(false);
            dataType.setFlag(false);
            dataType.setMessage("参数有误，请确认后重试");
        }
        return dataType;
    }

    @Override
    public DataType addOrEditOne(Rule rule) {
        if (rule.getId()!=null && rule.getType()>0 && rule.getContent()!=null){
            rule.setEdittime(new Date());
            if(ruleMapper.updateById(rule)>0){
                dataType.setData(rule);
                dataType.setFlag(true);
                dataType.setMessage("修改成功！！！");
            }else {
                dataType.setData(rule);
                dataType.setFlag(false);
                dataType.setMessage("修改失败，请确认参数是否有误后重试！！！");
            }
        }else if(rule.getType()>0 && rule.getContent()!=null){
            rule.setCreatetime(new Date());
            if(ruleMapper.insert(rule)>0){
                dataType.setData(rule);
                dataType.setFlag(true);
                dataType.setMessage("添加成功！！！");
            }else {
                dataType.setData(rule);
                dataType.setFlag(false);
                dataType.setMessage("添加失败，请确认参数是否有误后重试！！！");
            }
        }else {
            dataType.setData(null);
            dataType.setFlag(false);
            dataType.setMessage("操作失败，请检查后重试！！！");
        }
        return dataType;
    }
}




