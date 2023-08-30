package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 信息表
 * @TableName tcd_message
 */
@TableName(value ="tcd_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {
    /**
     * 信息编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色
     */
    @TableField(value = "role")
    private String role;

    /**
     * 消息类型：文字，图片、语音、视频
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 信息内容
     */
    @TableField(value = "message")
    private String message;

    /**
     * 消息发送者
     */
    @TableField(value = "from")
    private Long from;

    /**
     * 消息接收者
     */
    @TableField(value = "to")
    private Long to;

    /**
     * 消息真实发送者
     */
    @TableField(value = "sender")
    private Long sender;

    /**
     * 消息发送时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}