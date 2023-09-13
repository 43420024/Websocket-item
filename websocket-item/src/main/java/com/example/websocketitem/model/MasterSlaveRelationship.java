package com.example.websocketitem.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 主从账号表
 * @TableName tcd_master_slave_relationship
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterSlaveRelationship implements Serializable {
    /**
     * 主从主键
     */
    private Long masterSlaveId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 所属上级id(0为主账号，其他则自动找到所属主账号id)
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 昵称，级联查询
     */
    private String nickname;
    /**
     * 头像，级联查询
     */
    private String headPath;

    private static final long serialVersionUID = 1L;
}