package com.example.websocketitem.mapper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
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
    void selectAll() {
    }

    @Test
    void selectByTrendsIdAndCommentLevelOrderByTopStatusDescAndCreateTimeDesc() {
    }

    @Test
    void selectByParentCommentIdAndTrendsIdAndCommentLevelOrderByCreateTimeDesc() {
    }

    @Test
    void updatePraiseNumByCommentId() {
        int i = commentMapper.updatePraiseNumByCommentId(6, 1);
        log.info("更新返回值 {}",i);
        assert i == 1;
    }
}