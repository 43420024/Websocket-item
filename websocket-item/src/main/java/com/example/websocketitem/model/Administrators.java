package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 管理员表
 * @TableName tcd_administrators
 */
@TableName(value ="tcd_administrators")
@Data
public class Administrators implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员账号
     */
    @TableField(value = "manage_accounts")
    private Long manageAccounts;

    /**
     * 密码
     */
    @TableField(value = "passwords")
    private String passwords;

    /**
     * 权限
     */
    @TableField(value = "limitation")
    private Integer limitation;

    /**
     * 登录时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}