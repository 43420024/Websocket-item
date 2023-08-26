package com.example.websocketitem.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 积分表
 * @TableName tcd_points
 */
@Data
public class Points implements Serializable {
    /**
     * 积分id
     */
    private Integer pointsId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 总积分
     */
    private Integer totalPoints;

    /**
     * 当前等级
     */
    private String currentLevel;

    /**
     * 则扣率
     */
    private Integer discountRate;

    /**
     * 最后级别更新（评定）日期
     */

    private LocalDateTime lastLevelUpdateDate;

    private static final long serialVersionUID = 1L;
}