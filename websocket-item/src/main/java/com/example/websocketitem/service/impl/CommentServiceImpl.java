package com.example.websocketitem.service.impl;

import com.example.websocketitem.domain.Comment;
import com.example.websocketitem.mapper.CommentMapper;
import com.example.websocketitem.service.CommentService;
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
    public int updateByPrimaryKeySelective(Comment record) {
        commentMapper.updateByPrimaryKeySelective(record);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        commentMapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public List<Comment> selectAll() {
        commentMapper.selectAll();
        return null;
    }
}
