package com.example.websocketitem.service;

import com.example.websocketitem.domain.Comment;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {
    Result<Comment> deleteByPrimaryKey(Long id);

    Result<Comment> insert(Comment record);

    Result<Comment> insertSelective(Comment record);

    Result<PageInfo<Comment>> listCommentPage(int pageNum, int pageSize);
    Result<List<Comment>> selectByTrendsIdAndCommentLevel(Integer trendsId, Integer commentLevel);

    Result<List<Comment>> selectByParentCommentIdAndTrendsIdAndCommentLevel(Integer parentCommentId, Integer trendsId, Integer commentLevel);
}
