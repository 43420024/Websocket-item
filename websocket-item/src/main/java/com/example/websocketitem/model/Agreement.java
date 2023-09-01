package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 协议表
 * @TableName tcd_agreement
 */
@TableName(value ="tcd_agreement")
@Data
public class Agreement implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 协议标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 协议内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 创建id
     */
    @TableField(value = "administrators_id")
    private Integer administratorsId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}