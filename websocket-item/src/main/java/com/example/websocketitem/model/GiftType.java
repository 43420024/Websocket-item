package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName tcd_gift_type
 */
@TableName(value ="tcd_gift_type")
@Data
public class GiftType implements Serializable {
    /**
     * 礼物类型编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 礼物类型名称
     */
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}