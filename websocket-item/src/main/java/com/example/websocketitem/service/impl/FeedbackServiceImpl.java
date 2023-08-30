package com.example.websocketitem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocketitem.model.Feedback;
import com.example.websocketitem.service.FeedbackService;
import com.example.websocketitem.mapper.FeedbackMapper;
import com.example.websocketitem.utils.Result;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author cd
* @description 针对表【tcd_feedback(反馈表)】的数据库操作Service实现
* @createDate 2023-08-30 17:24:13
*/
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback>
    implements FeedbackService{
    /**
     * 添加一个反馈意见
     * */
    @Override
    public Result feedbackAdd(Feedback feedback) {
        feedback.setCreateTime(new Date());
        boolean save = super.save(feedback);
        return save?Result.ok():Result.error();
    }
    /**
     * 删除一个反馈意见
     * */
    @Override
    public Result feedbackDelete(Integer id) {
        boolean b = super.removeById(id);
        return b?Result.ok():Result.error();
    }


}




