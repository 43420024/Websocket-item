package com.example.websocketitem.service;

import cn.hutool.core.lang.tree.Tree;
import com.example.websocketitem.model.Comment;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public interface CommentService {
    Result<Comment> deleteByPrimaryKey(Long id);

    Result<Comment> insert(Comment record);

    Result<Comment> insertSelective(Comment record);

    Result<PageInfo<Comment>> listCommentPage(int pageNum, int pageSize);
    Result<List<Comment>> selectByArticleIdIdAndCommentLevel(Long articleId, Integer commentLevel);

    Result<List<Comment>> selectByParentCommentIdAndArticleIdIdAndCommentLevel(Long parentCommentId, Long articleId, Integer commentLevel);

    Result<Comment> updatePraiseNumByCommentId(Long commentId,@Min(value = 0,message = "点赞量不能为负数") Integer praiseNum);

    Result<PageInfo<Comment>> selectAllByUserId(Long userId, Integer pageNum, Integer pageSize);

    Result<List<Tree<Long>>> listCommentAll();

    Result<PageInfo<Comment>> selectByCreateTimeOneWeek(Integer pageNum, Integer pageSize);

}
