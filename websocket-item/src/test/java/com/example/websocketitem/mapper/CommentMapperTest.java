package com.example.websocketitem.mapper;

import com.example.websocketitem.model.Comment;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class CommentMapperTest {
    @Resource
    private CommentMapper commentMapper;

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insert() {
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void selectAll() {
    }

    @Test
    void selectByArticleIdAndCommentLevelOrderByTopStatusDescAndCreateTimeDesc() {
    }

    @Test
    void selectByParentCommentIdAndArticleIdAndCommentLevelOrderByCreateTimeDesc() {
    }

    @Test
    void updatePraiseNumByCommentId() {
    }

    @Test
    void selectAllByUserId() {
    }

    @Test
    void selectByCreateTimeOneWeek() {
        List<Comment> commentList = commentMapper.selectByCreateTimeOneWeek();
        commentList.forEach(comment -> log.info("{}",comment.toString()));
    }
}