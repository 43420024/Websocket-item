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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Administrators other = (Administrators) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getManageAccounts() == null ? other.getManageAccounts() == null : this.getManageAccounts().equals(other.getManageAccounts()))
            && (this.getPasswords() == null ? other.getPasswords() == null : this.getPasswords().equals(other.getPasswords()))
            && (this.getLimitation() == null ? other.getLimitation() == null : this.getLimitation().equals(other.getLimitation()))
            && (this.getLoginTime() == null ? other.getLoginTime() == null : this.getLoginTime().equals(other.getLoginTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getManageAccounts() == null) ? 0 : getManageAccounts().hashCode());
        result = prime * result + ((getPasswords() == null) ? 0 : getPasswords().hashCode());
        result = prime * result + ((getLimitation() == null) ? 0 : getLimitation().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", manageAccounts=").append(manageAccounts);
        sb.append(", passwords=").append(passwords);
        sb.append(", limitation=").append(limitation);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}