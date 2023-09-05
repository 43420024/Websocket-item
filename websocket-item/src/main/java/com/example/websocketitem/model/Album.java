package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 相册
 * @TableName tcd_album
 */
@TableName(value ="tcd_album")
@Data
public class Album implements Serializable {
    /**
     * 相册编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 相册创建人编号
     */
    private Long userId;

    /**
     * 相册名称
     */
    private String name;

    /**
     * 相册是否开放
     */
    private Integer openness;

    /**
     * 相册状态0:未审核(给看1:审核通过(给看2:审核不通过(冻结
     */
    private Integer status;

    /**
     * 相册总容量
     */
    private Integer capacity;

    /**
     * 相册创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}