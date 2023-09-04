package com.example.websocketitem.exception;

import com.example.websocketitem.enums.ResponseStatusEnum;
import com.example.websocketitem.utils.Result;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 全局异常捕获
 */
@ControllerAdvice
@ResponseBody
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

}
