package com.example.websocketitem.mapper;
import java.time.LocalDateTime;
import org.apache.ibatis.annotations.Param;

import com.example.websocketitem.model.Comment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author ASUS
* @description 针对表【tcd_comment(评论表)】的数据库操作Mapper
* @createDate 2023-08-26 15:23:03
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

    List<Comment> selectByArticleIdAndCommentLevelOrderByTopStatusDescAndCreateTimeDesc(@Param("articleId") Long articleId, @Param("commentLevel") Integer commentLevel);

    List<Comment> selectByParentCommentIdAndArticleIdAndCommentLevelOrderByCreateTimeDesc(@Param("parentCommentId") Long parentCommentId, @Param("articleId") Long articleId, @Param("commentLevel") Integer commentLevel);

    int updatePraiseNumByCommentId(@Param("praiseNum") Integer praiseNum, @Param("commentId") Long commentId);

    List<Comment> selectAllByUserId(@Param("userId") Long userId);

    List<Comment> selectByCreateTimeOneWeek();
    @Select("SELECT id FROM `tcd_trends`")
    List<Long> selectAllTrendsId();
    @Select("SELECT id FROM `tcd_user`")
    List<Long> selectAllUserId();
    @Select("select id from `tcd_album`")
    List<Long> selectAllPhotoAlbumId();
}
