package com.example.websocketitem.service.impl;

import com.example.websocketitem.domain.Comment;
import com.example.websocketitem.mapper.CommentMapper;
import com.example.websocketitem.service.CommentService;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        int deleteByPrimaryKey = commentMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Comment record) {
        commentMapper.insert(record);
        return 0;
    }

    @Override
    public int insertSelective(Comment record) {
        commentMapper.insertSelective(record);
        return 0;
    }

    @Override
    public Comment selectByPrimaryKey(Long id) {
        commentMapper.selectByPrimaryKey(id);
        return null;
    }

    @Override
    public Result<PageInfo<Comment>> listCommentPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> commentList = commentMapper.selectAll();
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        return Result.ok("查询成功",pageInfo);
    }

    @Override
    public Result<List<Comment>> selectByArticleIdAndCommentLevel(Integer articleId, Integer commentLevel) {
        List<Comment> commentList = this.commentMapper.selectByArticleIdAndCommentLevelOrderByTopStatusDescAndCreateTimeDesc(articleId, commentLevel);
        return Result.ok("查询成功",commentList);
    }


    @Override
    public Result<List<Comment>> selectByParentCommentIdAndArticleIdAndCommentLevel(Integer parentCommentId, Integer articleId, Integer commentLevel) {
        List<Comment> commentList = this.commentMapper.selectByParentCommentIdAndArticleIdAndCommentLevelOrderByCreateTimeDesc(parentCommentId, articleId, commentLevel);
        return Result.ok("查询成功",commentList);
    }
}
