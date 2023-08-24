package com.example.websocketitem.controller;

import com.example.websocketitem.domain.Comment;
import com.example.websocketitem.service.CommentService;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
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
        log.info("进来了");
        return commentService.listCommentPage(pageNum,pageSize);
    }
    @GetMapping("/levelOne")
    public Result<List<Comment>> selectByTrendsIdAndCommentLevel(Integer trendsId, Integer commentLevel){
        log.info("进来了");
        return commentService.selectByTrendsIdAndCommentLevel(trendsId, commentLevel);
    }

    @GetMapping("/levelTwo")
    public Result<List<Comment>> selectByParentCommentIdAndTrendsIdAndCommentLevel(Integer parentCommentId, Integer trendsId, Integer commentLevel){
        log.info("进来了");
        return commentService.selectByParentCommentIdAndTrendsIdAndCommentLevel(parentCommentId, trendsId, commentLevel);
    }
    @PutMapping("/praise")
    public Result<Comment> updatePraiseNumByCommentId(Integer commentId, Integer praiseNum){
        log.info("进来了");
        return commentService.updatePraiseNumByCommentId(commentId, praiseNum);
    }
}
