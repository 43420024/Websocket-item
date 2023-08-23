package com.example.websocketitem.controller;

import com.example.websocketitem.domain.Comment;
import com.example.websocketitem.service.CommentService;
import com.example.websocketitem.utils.Result;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;
    @GetMapping
    public Result<PageInfo<Comment>> listCommentALl(@RequestParam(defaultValue = "1") int pageNum,
                                                    @RequestParam(defaultValue = "10") int pageSize){
        return commentService.listCommentPage(pageNum,pageSize);
    }

    
    
    
}
