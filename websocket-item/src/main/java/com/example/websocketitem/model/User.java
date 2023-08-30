package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName tcd_user
 */
@TableName(value ="tcd_user")
@Data
public class User implements Serializable {
    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 用户状态：0：冻结，1：解冻
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private UserInfo userInfo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}