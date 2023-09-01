package com.example.websocketitem.controller;

import com.example.websocketitem.model.Feedback;
import com.example.websocketitem.service.FeedbackService;
import com.example.websocketitem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Resource
    private FeedbackService feedbackService;
    /**
     * 添加一个反馈意见
     * */
    @PostMapping("/feedbackAdd")
    public Result feedbackAdd(@RequestBody Feedback feedback){
        return feedbackService.feedbackAdd(feedback);
    }
    /**
     * 删除一个反馈意见
     * */
    @DeleteMapping("/feedbackDelete")
    public Result feedbackDelete(@RequestParam Integer id){
        return feedbackService.feedbackDelete(id);
    }
    /**
    * 删除多个
    * */
    @DeleteMapping("/feedbackDeleteAll")
    public Result feedbackDeleteAll(@RequestParam Integer[] id){
        return feedbackService.feedbackDeleteAll(id);
    }
    /**
     * 查询单个反馈意见
     * */
    @GetMapping("/feedbackById")
    public Result feedbackById(@RequestParam Integer id){
        return feedbackService.feedbackById(id);
    }
    /**
     * 查询全部反馈意见
     * */
    @GetMapping("/feedbackByAll")
    public Result feedbackByAll(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return feedbackService.feedbackByAll(pageNum,pageSize);
    }
}
