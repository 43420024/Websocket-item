package com.example.websocketitem.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 积分表
 * @TableName tcd_points
 */
@Data
public class Points implements Serializable {
    /**
     * 积分id
     */
    private Long pointsId;

    /**
     * 用户id
     */
    @NotNull
    private Long userId;

    /**
     * 总积分
     */
    private Integer totalPoints;

    /**
     * 当前等级
     */
    private String currentLevel;

    /**
     * 折扣率
     */
    private Integer discountRate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 最后级别更新（评定）日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLevelUpdateDate;

    private static final long serialVersionUID = 1L;
}