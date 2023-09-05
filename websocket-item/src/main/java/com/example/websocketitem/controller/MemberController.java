package com.example.websocketitem.controller;


import com.example.websocketitem.model.Member;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.model.SearchModel;
import com.example.websocketitem.service.MemberService;
import com.example.websocketitem.utils.DataType;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Member")
@Log4j
public class MemberController {
    @Resource
    private MemberService memberService;

    //查询单个会员等级信息
    @GetMapping("{id}")
    public DataType selectMember(@PathVariable Integer id){
        return memberService.selectOneMember(id);
    }

    //增加与修改单个会员等级
    @PostMapping("/addOrModifyMember")
    public DataType addMember(@RequestBody Member member){
        return memberService.addOrModifyMember(member);
    }

    //删除单个会员等级
    @DeleteMapping("/deleteOneMember/{id}")
    public DataType deleteMember(@PathVariable Integer id){
        return memberService.deleteMemberOne(id);
    }

    //会员等级分页查询
    @PostMapping("/paging/{page}/{size}")
    public ResponseMap paging(@PathVariable Integer page, @PathVariable Integer size){
        return memberService.pagingList(page,size);
    }


}
