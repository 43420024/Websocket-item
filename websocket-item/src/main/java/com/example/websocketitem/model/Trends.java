package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户动态表
 * @TableName tcd_trends
 */
@TableName(value ="tcd_trends")
@Data
public class Trends implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 动态所属人id
     */
    private Long userid;

    /**
     * 动态文字描述
     */
    private String content;

    /**
     * 动态图片
     */
    private String imgsrc;

    /**
     * 点赞数
     */
    private Long count;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 编辑时间
     */
    private Date edittime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}