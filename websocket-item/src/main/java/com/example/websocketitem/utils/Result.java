package com.example.websocketitem.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    //响应是否成功
    private boolean flag;
    //响应结果
    private String message;
    //响应数据
    private Object data;

    //成功响应静态方法
    public static Result success(){
        return new Result(true,"成功",null);
    }
    //成功响应静态方法（带有数据）
    public static Result success(Object data){
        return new Result(true,"成功",data);
    }
    //成功响应静态方法（带有响应结果）
    public static Result success(String message){
        return new Result(true,message,null);
    }
    //失败响应静态方法
    public static Result error(String message){
        return new Result(false,message,null);
    }

    // 成功静态方法
    public static Result ok() {
        Result r = new Result();
        r.setFlag(true);
        r.setMessage("成功");
        return r;
    }

    // 失败静态方法
    public static Result error() {
        Result r = new Result();
        r.setFlag(false);
        r.setMessage("失败");
        return r;
    }

    public Result success(Boolean success){
        this.setFlag(success);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }
    public Result data(Object object){
        this.setData(object);
        return this;
    }
}
