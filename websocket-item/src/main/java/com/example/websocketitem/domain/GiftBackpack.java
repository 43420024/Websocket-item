package com.example.websocketitem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName tcd_gift_backpack
 */
@TableName(value ="tcd_gift_backpack")
@Data
public class GiftBackpack implements Serializable {
    /**
     * 礼物背包编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 礼物编号
     */
    @TableField(value = "gift_id")
    private Integer giftId;

    /**
     * 礼物购买时间
     */
    @TableField(value = "buy_time")
    private LocalDateTime buyTime;

    /**
     * 数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}