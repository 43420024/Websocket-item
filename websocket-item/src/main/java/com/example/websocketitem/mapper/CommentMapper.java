package com.example.websocketitem.mapper;
import org.apache.ibatis.annotations.Param;

import com.example.websocketitem.domain.Comment;

import java.util.List;

/**
* @author ASUS
* @description 针对表【tcd_comment(评论表)】的数据库操作Mapper
* @createDate 2023-08-23 14:50:06
* @Entity com.example.websocketitem.domain.Comment
*/
public interface CommentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAll();

    /**
     * 按文章 ID 和评论级别选择 按顶部状态描述和创建时间描述排序
     * @param articleId
     * @param commentLevel
     * @return
     */
    List<Comment> selectByArticleIdAndCommentLevelOrderByTopStatusDescAndCreateTimeDesc(@Param("articleId") Integer articleId, @Param("commentLevel") Integer commentLevel);

    /**
     * 按父评论 ID 和文章 ID 和评论级别选择 按创建时间描述排序
     * @param parentCommentId
     * @param articleId
     * @param commentLevel
     * @return
     */
    List<Comment> selectByParentCommentIdAndArticleIdAndCommentLevelOrderByCreateTimeDesc(@Param("parentCommentId") Integer parentCommentId, @Param("articleId") Integer articleId, @Param("commentLevel") Integer commentLevel);
}
