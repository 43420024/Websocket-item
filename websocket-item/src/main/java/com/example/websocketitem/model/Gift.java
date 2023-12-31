package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName tcd_gift
 */
@TableName(value ="tcd_gift")
@Data
public class Gift implements Serializable {
    /**
     * 礼物编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 礼物名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 礼物图片路径
     */
    @TableField(value = "path")
    private String path;

    /**
     * 礼物价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 礼物创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 礼物类型
     */
    @TableField(value = "type_id")
    private Integer typeId;

    /**
     * 礼物修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 礼物状态1：上架 0：下架
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private GiftType giftType;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}