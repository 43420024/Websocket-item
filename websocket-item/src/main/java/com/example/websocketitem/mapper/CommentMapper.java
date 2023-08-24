package com.example.websocketitem.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.websocketitem.model.Comment;

/**
* @author ASUS
* @description 针对表【tcd_comment(评论表)】的数据库操作Mapper
* @createDate 2023-08-23 16:41:45
* @Entity com.example.websocketitem.model.Comment
*/
public interface CommentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAll();
    List<Comment> selectByTrendsIdAndCommentLevelOrderByTopStatusDescAndCreateTimeDesc(@Param("trendsId") Integer trendsId, @Param("commentLevel") Integer commentLevel);

    List<Comment> selectByParentCommentIdAndTrendsIdAndCommentLevelOrderByCreateTimeDesc(@Param("parentCommentId") Integer parentCommentId, @Param("trendsId") Integer trendsId, @Param("commentLevel") Integer commentLevel);

    int updatePraiseNumByCommentId(@Param("commentId") Integer commentId, @Param("praiseNum") Integer praiseNum);
}
