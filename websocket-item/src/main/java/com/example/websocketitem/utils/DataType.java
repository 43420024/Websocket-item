package com.example.websocketitem.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataType {

    //响应是否成功
    private boolean flag;

    //提示信息
    private String message;

    //响应数据
    private Object data;



}
