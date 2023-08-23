package com.example.websocketitem.service;

import com.example.websocketitem.domain.Comment;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    Result<PageInfo<Comment>> listCommentPage(int pageNum, int pageSize);
    Result<List<Comment>> selectByArticleIdAndCommentLevel(Integer articleId, Integer commentLevel);

    Result<List<Comment>> selectByParentCommentIdAndArticleIdAndCommentLevel(Integer parentCommentId, Integer articleId, Integer commentLevel);
}
