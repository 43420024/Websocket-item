package com.example.websocketitem.service;

import com.example.websocketitem.model.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocketitem.utils.Result;

/**
* @author cd
* @description 针对表【tcd_feedback(反馈表)】的数据库操作Service
* @createDate 2023-08-30 17:24:13
*/
public interface FeedbackService extends IService<Feedback> {
    Result feedbackAdd(Feedback feedback);
    Result feedbackDelete(Integer id);
    Result feedbackById(Integer id);
    Result feedbackByAll(Integer pageNum,Integer pageSize);
    Result feedbackDeleteAll(Integer[] id);
}
