package com.example.websocketitem.service;

import com.example.websocketitem.model.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.model.ResponseMap;
import com.example.websocketitem.utils.DataType;

/**
* @author cd
* @description 针对表【tcd_member(会员等级表)】的数据库操作Service
* @createDate 2023-09-01 09:49:56
*/
public interface MemberService extends IService<Member> {

    DataType selectOneMember(Integer id);

    DataType addOrModifyMember(Member member);

    DataType deleteMemberOne(Integer id);

    ResponseMap pagingList(Integer page, Integer size);
}
