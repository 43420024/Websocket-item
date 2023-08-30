package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 系统电话表
 * @TableName tcd_system_telephone
 */
@TableName(value ="tcd_system_telephone")
@Data
public class SystemTelephone implements Serializable {
    /**
     * 电话主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 号码
     */
    @TableField(value = "phone_number")
    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "电话有误")
    private String phoneNumber;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}