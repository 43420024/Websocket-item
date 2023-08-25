package com.example.websocketitem.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.example.websocketitem.mapper.CommentMapper;
import com.example.websocketitem.model.Comment;
import com.example.websocketitem.service.CommentService;
import com.example.websocketitem.utils.Result;
import com.example.websocketitem.utils.SensitivewordUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
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
        if(SensitivewordUtil.illegal>0){ // 包含非法内容则设置为1（非法）
            record.setIllegal(1);
        }
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
    public Result<List<Comment>> selectByArticleIdIdAndCommentLevel(Integer articleId, Integer commentLevel) {
        List<Comment> commentList = this.commentMapper.selectByArticleIdAndCommentLevelOrderByTopStatusDescAndCreateTimeDesc(articleId, commentLevel);
        return Result.ok("查询成功",commentList);
    }


    @Override
    public Result<List<Comment>> selectByParentCommentIdAndArticleIdIdAndCommentLevel(Integer parentCommentId, Integer articleId, Integer commentLevel) {
        List<Comment> commentList = this.commentMapper.selectByParentCommentIdAndArticleIdAndCommentLevelOrderByCreateTimeDesc(parentCommentId, articleId, commentLevel);
        return Result.ok("查询成功",commentList);
    }

    @Override
    public Result<Comment> updatePraiseNumByCommentId(Integer commentId, Integer praiseNum) {
        int updatePraiseNumByCommentId = commentMapper.updatePraiseNumByCommentId(praiseNum,commentId);
        if(updatePraiseNumByCommentId>0){
            return Result.ok("点赞成功");
        }
        return Result.error("点赞失败");
    }

    @Override
    public Result<PageInfo<Comment>> selectAllByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> commentList = commentMapper.selectAllByUserId(userId);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        return Result.ok("查询成功",pageInfo);
    }

    @Override
    public Result<List<Tree<Integer>>> listCommentAll() {
        List<Comment> commentList = commentMapper.selectAll();
        // 创建 TreeNodeConfig 配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("commentId"); // 设置节点权重字段，这里使用 commentId 作为权重
        // 构建菜单树
        List<Tree<Integer>> treeList = TreeUtil.build(commentList, null, treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getCommentId());
                    tree.setParentId(treeNode.getParentCommentId());
                    tree.setWeight(treeNode.getCommentId()); // 设置节点权重
                    tree.setName(treeNode.getCommentContent()); // 设置节点名称
                    tree.putExtra("userId", treeNode.getUserId());
                    tree.putExtra("articleId", treeNode.getArticleId());
                    tree.putExtra("praiseNum", treeNode.getPraiseNum());
                    tree.putExtra("topStatus", treeNode.getTopStatus());
                    tree.putExtra("parentCommentId", treeNode.getReplyCommentId());
                    tree.putExtra("parentCommentUserId", treeNode.getParentCommentUserId());
                    tree.putExtra("replyCommentId", treeNode.getReplyCommentId());
                    tree.putExtra("replyCommentUserId", treeNode.getReplyCommentUserId());
                    tree.putExtra("commentLeave", treeNode.getCommentLevel());
                    tree.putExtra("createTime", treeNode.getCreateTime());
                });
        return Result.ok("查询成功",treeList);
    }

    @Override
    public Result<PageInfo<Comment>> selectByCreateTimeOneWeek(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> commentList = this.commentMapper.selectByCreateTimeOneWeek();
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        return Result.ok("查询成功",pageInfo);
    }
}
