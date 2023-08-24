package com.example.websocketitem.enums;


import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    PARAM_ERROR(false, "参数错误");
    private final Boolean flag;
    private final String message;
    ErrorCodeEnum(Boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }
}
