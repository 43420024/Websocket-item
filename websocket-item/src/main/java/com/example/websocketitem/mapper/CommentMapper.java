package com.example.websocketitem.mapper;
import java.util.Date;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.websocketitem.model.Comment;

/**
* @author ASUS
* @description 针对表【tcd_comment(评论表)】的数据库操作Mapper
* @createDate 2023-08-25 14:42:48
* @Entity com.example.websocketitem.model.Comment
*/
public interface CommentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAll();

    List<Comment> selectByArticleIdAndCommentLevelOrderByTopStatusDescAndCreateTimeDesc(@Param("articleId") Integer articleId, @Param("commentLevel") Integer commentLevel);

    List<Comment> selectByParentCommentIdAndArticleIdAndCommentLevelOrderByCreateTimeDesc(@Param("parentCommentId") Integer parentCommentId, @Param("articleId") Integer articleId, @Param("commentLevel") Integer commentLevel);

    int updatePraiseNumByCommentId(@Param("praiseNum") Integer praiseNum, @Param("commentId") Integer commentId);

    List<Comment> selectAllByUserId(@Param("userId") Integer userId);

    List<Comment> selectByCreateTimeOneWeek();
}
