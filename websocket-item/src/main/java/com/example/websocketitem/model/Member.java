package com.example.websocketitem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 会员等级表
 * @TableName tcd_member
 */
@TableName(value ="tcd_member")
@Data
public class Member implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 会员等级
     */
    private String grade;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date modifytime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                ", createtime=" + createtime +
                ", modifytime=" + modifytime +
                '}';
    }
}
