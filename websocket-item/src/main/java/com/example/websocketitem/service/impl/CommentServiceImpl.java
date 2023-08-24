package com.example.websocketitem.service.impl;

import com.example.websocketitem.model.Comment;
import com.example.websocketitem.mapper.CommentMapper;
import com.example.websocketitem.service.CommentService;
import com.example.websocketitem.utils.Result;
import com.example.websocketitem.utils.SensitivewordUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Override
    public Result<Comment> deleteByPrimaryKey(Long id) {
        int deleteByPrimaryKey = commentMapper.deleteByPrimaryKey(id);
        if(deleteByPrimaryKey>0){
            return Result.ok("删除成功");
        }
        return Result.error("删除失败");
    }

    @Override
    public Result<Comment> insert(Comment record) {
        record.setCreateTime(new Date());
        int insert = commentMapper.insert(record);
        if(insert>0){
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");
    }

    @Override
    public Result<Comment> insertSelective(Comment record) {
        record.setCommentContent(SensitivewordUtil.replaceSensitiveWord(record.getCommentContent(), 1, "*"));
        record.setCreateTime(new Date());
        int insertSelective = commentMapper.insertSelective(record);
        if (insertSelective> 0) {
            return Result.ok("添加成功",record);
        }
        return Result.error("添加失败");
    }

    @Override
    public Result<PageInfo<Comment>> listCommentPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> commentList = commentMapper.selectAll();
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        return Result.ok("查询成功",pageInfo);
    }

    @Override
    public Result<List<Comment>> selectByTrendsIdAndCommentLevel(Integer trendsId, Integer commentLevel) {
        List<Comment> commentList = this.commentMapper.selectByTrendsIdAndCommentLevelOrderByTopStatusDescAndCreateTimeDesc(trendsId, commentLevel);
        return Result.ok("查询成功",commentList);
    }


    @Override
    public Result<List<Comment>> selectByParentCommentIdAndTrendsIdAndCommentLevel(Integer parentCommentId, Integer trendsId, Integer commentLevel) {
        List<Comment> commentList = this.commentMapper.selectByParentCommentIdAndTrendsIdAndCommentLevelOrderByCreateTimeDesc(parentCommentId, trendsId, commentLevel);
        return Result.ok("查询成功",commentList);
    }

    @Override
    public Result<Comment> updatePraiseNumByCommentId(Integer commentId, Integer praiseNum) {
        int updatePraiseNumByCommentId = commentMapper.updatePraiseNumByCommentId(commentId,praiseNum);
        if(updatePraiseNumByCommentId>0){
            return Result.ok("点赞成功");
        }
        return Result.error("点赞失败");
    }
}
