package com.example.websocketitem.mapper;

import com.example.websocketitem.domain.Comment;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class CommentMapperTest {
    @Resource
    private CommentMapper commentMapper;
    @Test
    void selectByPrimaryKey() {
        Comment comment = commentMapper.selectByPrimaryKey(1L);
        log.info(comment.toString());
    }
    @Test
    void selectAll() {
        List<Comment> comments = commentMapper.selectAll();
        log.info(comments.toString());
    }

}