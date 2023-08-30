package com.example.websocketitem.enums;


import lombok.Getter;

@Getter
public enum ResponseStatusEnum {
    ADD_SUCCESS(true,"添加成功"),
    DELETE_SUCCESS(true,"删除成功"),
    UPDATE_SUCCESS(true,"修改成功"),
    QUERY_SUCCESS(true,"查询成功"),
    PRAISE_SUCCESS(true,"点赞成功"),
    POINTS_PLUS_SUCCESS(true,"积分添加成功"),
    ADD_FAILURE(false,"添加失败"),
    DELETE_FAILURE(false,"删除失败"),
    UPDATE_FAILURE(false,"修改失败"),
    QUERY_FAILURE(false,"查询失败"),
    PRAISE_FAILURE(false,"点赞失败"),
    POINTS_PLUS_FAILURE(false,"积分添加失败"),
    PARAM_ERROR(false, "参数错误"),
    USER_NOT_EXIST(false,"用户不存在"),
    RESOURCE_NOT_EXIST(false,"资源不存在"),
    ARTICLE_NOT_EXIST(false,"文章不存在");
    private final Boolean flag;
    private final String message;
    ResponseStatusEnum(Boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }
}
