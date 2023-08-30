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
}
