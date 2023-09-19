package com.example.websocketitem.model;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Array;
import java.util.Date;
import java.util.List;

import com.example.websocketitem.model.Album;
import com.example.websocketitem.model.Points;
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
     * 电话号码
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 微信登录
     */
    @TableField(value = "chat_login")
    private String chatLogin;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户状态：0：冻结，1：解冻
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;


    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 相册
     */
    @TableField(exist = false)
    private List<Album> albums;

    /**
     * 积分
     */
    @TableField(exist = false)
    private Points points;

    @TableField(exist = false)
    private Integer reportCount;

    @TableField(exist = false)
    private Integer albumCount;

    //用于接收数组，在数据表中并不存在
    @TableField(exist = false)
    @JsonProperty(value ="labelsArray")
    private JSONArray labelsArray;

    @TableField(exist = false)
    private Integer unreadMessageNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}