package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName tcd_album
 */
@TableName(value ="tcd_album")
@Data
public class Album implements Serializable {
    private Long id;

    private Long userId;

    private String name;

    private Integer openness;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}