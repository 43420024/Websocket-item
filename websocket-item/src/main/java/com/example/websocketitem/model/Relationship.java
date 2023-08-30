package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 好友关系表
 * @TableName tcd_relationship
 */
@TableName(value ="tcd_relationship")
@Data
@NoArgsConstructor
public class Relationship implements Serializable {
    /**
     * 好友关系编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 主人id
     */
    private Long ownerId;

    /**
     * 好友id
     */
    private Long friendId;

    /**
     * 好友关系创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}