package com.example.websocketitem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Array;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName tcd_user_info
 */
@TableName(value ="tcd_user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 
     */
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long InfoId;

    /**
     * 用户性别：0：男，1女
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 个性标签
     */
    @TableField(value = "labels")
    private String labels;

    /**
     * 头像图片路径
     */
    @TableField(value = "head_path")
    private String headPath;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 职称
     */
    @TableField(value = "job_title")
    private String jobTitle;

    /**
     * 地区
     */
    @TableField(value = "region")
    private String region;

    /**
     * 语言介绍
     */
    @TableField(value = "introduction")
    private String introduction;

    /**
     * 点赞数量
     */
    @TableField(value = "like_number")
    private Integer likeNumber;

    /**
     * 个人描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 积分
     */
    @TableField(value = "integral")
    private Integer integral;

    //用于接收数组，在数据表中并不存在
    @TableField(exist = false)
    @JsonProperty(value ="labelsArray")
    private String[] labelsArray;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}