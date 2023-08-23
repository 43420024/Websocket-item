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
    private Integer commentator;

    /**
     * 评论目标
     */
    private String commentTarget;

    /**
     * 评论是否非法 1(非法)，0(合法)
     */
    private Integer illegal;

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
            && (this.getCommentator() == null ? other.getCommentator() == null : this.getCommentator().equals(other.getCommentator()))
            && (this.getCommentTarget() == null ? other.getCommentTarget() == null : this.getCommentTarget().equals(other.getCommentTarget()))
            && (this.getIllegal() == null ? other.getIllegal() == null : this.getIllegal().equals(other.getIllegal()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getCommentContent() == null) ? 0 : getCommentContent().hashCode());
        result = prime * result + ((getCommentator() == null) ? 0 : getCommentator().hashCode());
        result = prime * result + ((getCommentTarget() == null) ? 0 : getCommentTarget().hashCode());
        result = prime * result + ((getIllegal() == null) ? 0 : getIllegal().hashCode());
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
        sb.append(", commentator=").append(commentator);
        sb.append(", commentTarget=").append(commentTarget);
        sb.append(", illegal=").append(illegal);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}