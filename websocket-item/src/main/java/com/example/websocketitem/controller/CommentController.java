package com.example.websocketitem.controller;

import cn.hutool.core.lang.tree.Tree;
import com.example.websocketitem.model.Comment;
import com.example.websocketitem.service.CommentService;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("comment")
public class CommentController {
    @Resource
    private CommentService commentService;
    @GetMapping
    public Result<PageInfo<Comment>> listCommentALl(@RequestParam(defaultValue = "1") int pageNum,
                                                    @RequestParam(defaultValue = "10") int pageSize){
        return commentService.listCommentPage(pageNum,pageSize);
    }
    @GetMapping("/levelOne")
    public Result<List<Comment>> selectByTrendsIdAndCommentLevel(Long articleId, Integer commentLevel){
        return commentService.selectByArticleIdIdAndCommentLevel(articleId, commentLevel);
    }
    @GetMapping("/levelTwo")
    public Result<List<Comment>> selectByParentCommentIdAndTrendsIdAndCommentLevel(Long parentCommentId, Long articleId, Integer commentLevel){
        return commentService.selectByParentCommentIdAndArticleIdIdAndCommentLevel(parentCommentId, articleId, commentLevel);
    }
    @PutMapping("/praise")
    public Result<Comment> updatePraiseNumByCommentId(Long commentId, Integer praiseNum){
        return commentService.updatePraiseNumByCommentId(commentId, praiseNum);
    }
    @PostMapping
    public Result<Comment> addComment(@RequestBody @Valid Comment comment){
        return commentService.insertSelective(comment);
    }
    @DeleteMapping("/{id}")
    public Result<Comment> addComment(@PathVariable Long id){
        return commentService.deleteByPrimaryKey(id);
    }
    @GetMapping("/userId")
    public Result<PageInfo<Comment>> getCommentByUserId(@RequestParam Long userId, @RequestParam(defaultValue = "1") int pageNum,
                                           @RequestParam(defaultValue = "10") int pageSize){
        return commentService.selectAllByUserId(userId,pageNum,pageSize);
    }
    @GetMapping("/tree")
    public Result<List<Tree<Long>>> listCommentALlTree(){
        return commentService.listCommentAll();
    }
    @GetMapping("/oneWeek")
    public Result<PageInfo<Comment>> selectByCreateTimeOneWeek(@RequestParam(defaultValue = "1") int pageNum,
                                                               @RequestParam(defaultValue = "10") int pageSize){
        return commentService.selectByCreateTimeOneWeek(pageNum,pageSize);
    }
}
