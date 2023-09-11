package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.factory.DateTypeFactory;
import com.example.websocketitem.model.Member;
import com.example.websocketitem.model.Report;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.service.MemberService;
import com.example.websocketitem.mapper.MemberMapper;
import com.example.websocketitem.utils.DataType;
import com.example.websocketitem.utils.PageUtil;
import com.example.websocketitem.utils.ResponseMapUtil;
import com.example.websocketitem.utils.WrapperUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
* @author cd
* @description 针对表【tcd_member(会员等级表)】的数据库操作Service实现
* @createDate 2023-09-01 09:49:56
*/
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService{
    @Resource
    MemberMapper memberMapper;
    DataType dataType=DateTypeFactory.create();
    @Resource
    ResponseMapUtil<Member> responseMapUtil;
    @Resource
    PageUtil<Member> pageUtil;
    @Resource
    WrapperUtil<Member> wrapperUtil;

    @Override
    public DataType selectOneMember(Integer id) {
        if (id!=null){
            Member member = memberMapper.selectById(id);
            if (member!=null){
                dataType.setData(member);
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
    public DataType addOrModifyMember(Member member) {
        if (member.getGrade()!=null && member.getGrade().length()>0 && member.getId()!=null && member.getId()>0){
            Member member1 = memberMapper.selectById(member.getId());
            member.setCreatetime(member1.getCreatetime());
            member.setModifytime(new Date());
            memberMapper.updateById(member);
            dataType.setData(member);
            dataType.setFlag(true);
            dataType.setMessage("修改成功");
        }else if (member.getGrade()!=null && member.getGrade().length()>0){
            member.setCreatetime(new Date());
            memberMapper.insert(member);
            dataType.setData(member);
            dataType.setFlag(true);
            dataType.setMessage("添加成功");
        }else {
            dataType.setData(null);
            dataType.setFlag(false);
            dataType.setMessage("操作失败,请核实参数是否正确！！！");
        }
        return dataType;
    }

    @Override
    public DataType deleteMemberOne(Integer id) {
        if (id!=null){
            int i = memberMapper.deleteById(id);
            if (i>0){
                dataType.setData(true);
                dataType.setMessage("删除成功");
                dataType.setFlag(true);
            }else {
                dataType.setData(false);
                dataType.setMessage("删除失败");
                dataType.setFlag(false);
            }
        }else {
            dataType.setData(null);
            dataType.setFlag(false);
            dataType.setMessage("操作失败，请核实参数是否有误！");
        }
        return dataType;
    }

    @Override
    public ResponseMap pagingList(Integer page, Integer size) {
        Page<Member> pageList=this.page(pageUtil.getModelPage(page,size));
        Map<String,Object> modelMap=pageUtil.getModelMap(pageList);
        return responseMapUtil.getPageList(pageList,modelMap);
    }
}




