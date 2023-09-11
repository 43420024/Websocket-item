package com.example.websocketitem.exception;

import com.example.websocketitem.enums.ResponseStatusEnum;
import com.example.websocketitem.utils.Result;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 全局异常捕获
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    // controller层数据校验异常捕获
    @ExceptionHandler
    public Result<Object> handleException(MethodArgumentNotValidException e){
        Map<String, String> collect = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return Result.error(ResponseStatusEnum.PARAM_ERROR,collect);
    }
    // service层数据校验异常捕获
    @ExceptionHandler
    public Result<Object> handleException(ConstraintViolationException e){
        Map<Path, String> collect = e.getConstraintViolations().stream()
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
        return Result.error(ResponseStatusEnum.PARAM_ERROR,collect);
    }
    // 前后端请求参数不一致（缺少Servlet请求参数异常）
    @ExceptionHandler
    public Result<Object> handleException(MissingServletRequestParameterException e){
        Map<String, String> map = Stream.of(new AbstractMap.SimpleEntry<>("正确参数名", e.getParameterName()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return Result.error(ResponseStatusEnum.PARAM_NAME_BIND_ERROR,map);
    }
    // 前后端请求参数不一致（缺少Servlet请求参数异常）
    @ExceptionHandler
    public Result<Object> handleException(MissingServletRequestPartException e){
        Map<String, String> map = new HashMap<>();
        map.put("正确参数名",e.getRequestPartName());
        return Result.error(ResponseStatusEnum.PARAM_NAME_BIND_ERROR,map);
    }

}
