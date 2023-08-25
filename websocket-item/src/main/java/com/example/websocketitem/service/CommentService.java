package com.example.websocketitem.service;

import cn.hutool.core.lang.tree.Tree;
import com.example.websocketitem.model.Comment;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {
    Result<Comment> deleteByPrimaryKey(Long id);

    Result<Comment> insert(Comment record);

    Result<Comment> insertSelective(Comment record);

    Result<PageInfo<Comment>> listCommentPage(int pageNum, int pageSize);
    Result<List<Comment>> selectByArticleIdIdAndCommentLevel(Integer trendsId, Integer commentLevel);

    Result<List<Comment>> selectByParentCommentIdAndArticleIdIdAndCommentLevel(Integer parentCommentId, Integer trendsId, Integer commentLevel);

    Result<Comment> updatePraiseNumByCommentId(Integer commentId, Integer praiseNum);

    Result<PageInfo<Comment>> selectAllByUserId(Integer userId, Integer pageNum, Integer pageSize);

    Result<List<Tree<Integer>>> listCommentAll();

    Result<PageInfo<Comment>> selectByCreateTimeOneWeek(Integer pageNum, Integer pageSize);
}
