package com.example.websocketitem.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 评论表
 * @TableName tcd_comment
 */
@Data
public class Comment implements Serializable {
    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论人id
     */
    private Long userId;

    /**
     * 在哪篇文章（动态）下评论的
     */
    private Long articleId;

    /**
     * 文章类型（0动态，1相册，2其他）
     */
    private Integer articleType;

    /**
     * 评论是否非法 1(非法)，0(合法)
     */
    private Integer illegal;

    /**
     * 父级评论id
     */
    private Long parentCommentId;

    /**
     * 父级评论的user_id
     */
    private Long parentCommentUserId;

    /**
     * 被回复的评论id
     */
    private Long replyCommentId;

    /**
     * 被回复的评论的user_id
     */
    private Long replyCommentUserId;

    /**
     * 评论等级[ 1 一级评论 默认 ，2 二级评论]
     */
    private Integer commentLevel;

    /**
     * 评论的点赞数量
     */
    private Integer praiseNum;

    /**
     * 置顶状态[ 1 置顶，0 不置顶 默认 ]
     */
    private Integer topStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}