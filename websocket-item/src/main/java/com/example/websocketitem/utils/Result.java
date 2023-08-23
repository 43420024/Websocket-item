package com.example.websocketitem.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "create")  // 全参构造方法起别名
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)  // 为空置则不显示
@Builder    // 构造者模式
public class Result<T> {
    /**
     * 响应状态
     */
    private Boolean flag;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data ;
    //成功响应静态方法
    public static <T> Result<T> success(){
        return create(true,"成功",null);
    }
    //成功响应静态方法（带有数据）
    public static <T> Result<T> success(T data){
        return create(true,"成功",data);
    }
    //成功响应静态方法（带有响应结果）
    public static <T> Result<T> success(String message){
        return create(true,message,null);
    }
    // 成功静态方法
    public static <T> Result<T> ok() {
        return create(true,"成功",null);
    }

    // 失败静态方法
    public static <T> Result<T> error() {
        return create(false,"失败",null);
    }

    public Result<T> success(Boolean success){
        this.setFlag(success);
        return this;
    }

    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }
    public Result<T> data(T object){
        this.setData(object);
        return this;
    }
    /**
     * 操作成功。
     * @param message 响应消息
     * @return 200状态码
     * @param <T> 泛型
     */
    public static <T> Result<T> ok(String message){
        return create(true,message,null);
    }
    /**
     * 操作成功。
     * @param message 响应消息
     * @param data 响应数据
     * @return 200状态码
     * @param <T> 泛型
     */
    public static <T> Result<T> ok(String message, T data){
        return create(true,message,data);
    }
    /**
     * 操作失败。
     * @param message 响应消息
     * @return flag 状态码
     * @param <T> 泛型
     */
    public static <T> Result<T> error(String message){
        return create(false,message,null);
    }
    /**
     * 操作失败。
     * @param message 响应消息
     * @param data 响应数据
     * @return flag 状态码
     */
    public static <T> Result<T> error(String message,T data){
        return create(false,message,data);
    }
}
