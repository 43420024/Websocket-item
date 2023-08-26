package com.example.websocketitem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class AopLog {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    ThreadLocal<Long> startTime=new ThreadLocal<>();

    @Pointcut(value = "execution(* com.example.websocketitem.controller.*.*(..))")
    public void aopWebLog(){}

    @Around("aopWebLog()")
    public Object myLogger(ProceedingJoinPoint pjp) throws Throwable{
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request=attributes.getRequest();
        String className=pjp.getSignature().getDeclaringTypeName();
        String methodName=pjp.getSignature().getName();

        Object[] array=pjp.getArgs();
        ObjectMapper mapper=new ObjectMapper();

        logger.info("调用前：{}：{}，传递的参数为：{}",className,methodName,mapper.writeValueAsString(array));
        logger.info("URL:{}",request.getRequestURL().toString());
        logger.info("IP地址:{}",request.getRemoteAddr());

        Object obj=pjp.proceed();

        logger.info("调用后：{}：{}，返回值为：{}",className,methodName,mapper.writeValueAsString(obj));
        logger.info("耗时：{}ms",System.currentTimeMillis()- startTime.get());
        return obj;

    }



}
