package com.example.websocketitem.service;

import com.example.websocketitem.domain.Comment;

import java.util.List;

public interface CommentService {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAll();
}
