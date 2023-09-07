package com.example.websocketitem.model;

import java.io.Serializable;
import lombok.Data;

/**
 * 积分规则表
 * @TableName tcd_points_rules
 */
@Data
public class PointsRules implements Serializable {
    /**
     * 积分规则id
     */
    private Integer pointsRulesId;

    /**
     * 积分规则具体内容
     */
    private String pointsRulesContent;

    /**
     * 积分规则标题
     */
    private String pointsRulesPointsRules;

    private static final long serialVersionUID = 1L;
}