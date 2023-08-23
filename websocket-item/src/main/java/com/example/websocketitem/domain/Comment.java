package com.example.websocketitem.domain;

import java.io.Serializable;
import java.util.Date;
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
    private Integer commentId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论人id
     */
    private Integer userId;

    /**
     * 在哪篇文章（动态）下评论的
     */
    private Integer articleId;

    /**
     * 评论是否非法 1(非法)，0(合法)
     */
    private Integer illegal;

    /**
     * 父级评论id
     */
    private Integer parentCommentId;

    /**
     * 父级评论的user_id
     */
    private Integer parentCommentUserId;

    /**
     * 被回复的评论id
     */
    private Integer replyCommentId;

    /**
     * 被回复的评论的user_id
     */
    private Integer replyCommentUserId;

    /**
     * 评论级别，回复文章（动态）的是一级评论，其他的都是二级评论
     */
    private Integer commentLevel;

    /**
     * 评论的点赞数量
     */
    private Integer praiseNum;

    /**
     * 评论是否置顶
     */
    private Integer topStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getCommentContent() == null ? other.getCommentContent() == null : this.getCommentContent().equals(other.getCommentContent()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getIllegal() == null ? other.getIllegal() == null : this.getIllegal().equals(other.getIllegal()))
            && (this.getParentCommentId() == null ? other.getParentCommentId() == null : this.getParentCommentId().equals(other.getParentCommentId()))
            && (this.getParentCommentUserId() == null ? other.getParentCommentUserId() == null : this.getParentCommentUserId().equals(other.getParentCommentUserId()))
            && (this.getReplyCommentId() == null ? other.getReplyCommentId() == null : this.getReplyCommentId().equals(other.getReplyCommentId()))
            && (this.getReplyCommentUserId() == null ? other.getReplyCommentUserId() == null : this.getReplyCommentUserId().equals(other.getReplyCommentUserId()))
            && (this.getCommentLevel() == null ? other.getCommentLevel() == null : this.getCommentLevel().equals(other.getCommentLevel()))
            && (this.getPraiseNum() == null ? other.getPraiseNum() == null : this.getPraiseNum().equals(other.getPraiseNum()))
            && (this.getTopStatus() == null ? other.getTopStatus() == null : this.getTopStatus().equals(other.getTopStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getCommentContent() == null) ? 0 : getCommentContent().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getIllegal() == null) ? 0 : getIllegal().hashCode());
        result = prime * result + ((getParentCommentId() == null) ? 0 : getParentCommentId().hashCode());
        result = prime * result + ((getParentCommentUserId() == null) ? 0 : getParentCommentUserId().hashCode());
        result = prime * result + ((getReplyCommentId() == null) ? 0 : getReplyCommentId().hashCode());
        result = prime * result + ((getReplyCommentUserId() == null) ? 0 : getReplyCommentUserId().hashCode());
        result = prime * result + ((getCommentLevel() == null) ? 0 : getCommentLevel().hashCode());
        result = prime * result + ((getPraiseNum() == null) ? 0 : getPraiseNum().hashCode());
        result = prime * result + ((getTopStatus() == null) ? 0 : getTopStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", commentContent=").append(commentContent);
        sb.append(", userId=").append(userId);
        sb.append(", articleId=").append(articleId);
        sb.append(", illegal=").append(illegal);
        sb.append(", parentCommentId=").append(parentCommentId);
        sb.append(", parentCommentUserId=").append(parentCommentUserId);
        sb.append(", replyCommentId=").append(replyCommentId);
        sb.append(", replyCommentUserId=").append(replyCommentUserId);
        sb.append(", commentLevel=").append(commentLevel);
        sb.append(", praiseNum=").append(praiseNum);
        sb.append(", topStatus=").append(topStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}