package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 举报信息
 * @TableName tcd_report
 */
@TableName(value ="tcd_report")
@Data
public class Report implements Serializable {
    /**
     * 举报记录编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 检举人id
     */
    private Long informerId;

    /**
     * 被举报人id
     */
    private Long reporterId;

    /**
     * 举报信息类型
     */
    private Integer type;

    /**
     * 被举报内容编号
     */
    private Long contentId;

    /**
     * 举报信息描述
     */
    private String description;

    /**
     * 举报信息描述图片路径
     */
    private String descriptionImagePath;

    /**
     * 处理状态
     */
    private Integer status;

    /**
     * 举报记录时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}