package com.fiee.blog.aspect;


import com.alibaba.fastjson.JSON;
import com.fiee.blog.annotation.OptLog;
import com.fiee.blog.entity.OperationLog;
import com.fiee.blog.service.OperationLogService;
import com.fiee.blog.utils.IpUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 * @Author: Fiee
 * @ClassName: OptLogAspect 操作日志切面处理
 * @Date: 2025/12/17
 * @Version: v1.0.0
 **/
@Aspect
@Component
public class OptLogAspectAround {
    @Autowired
    private OperationLogService operationLogService;
    @Around("@annotation(optLog)")
    public Object logOperation(ProceedingJoinPoint joinPoint, OptLog optLog) throws Throwable{
        // 获取 RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取 HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes).resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //开始执行时间
        LocalDateTime start = LocalDateTime.now();
        OperationLog operationLog = buildLogEntity(optLog,request,joinPoint);
        try {
            Object result = joinPoint.proceed();
            // 响应结果
            operationLog.setResponseData(JSON.toJSONString(result));
            // 执行时长
            operationLog.setExecuteTime((int) Duration.between(start, LocalDateTime.now()).toMillis());
            return result;
         } catch (Exception e) {
            operationLog.setResponseData(JSON.toJSONString(e.getMessage()));
            throw e;
        }finally {
            // 保存日志 TODO 优化：利用Kafka 消息队列
//            operationLogService.saveOperationLogAsync(operationLog);
            operationLogService.sendToKafkaToSaveLog(operationLog);
        }
    }

    /**
     * Log 实体对象
     * @param optLog
     * @param request
     * @param joinPoint
     * @return
     */
    private OperationLog buildLogEntity(OptLog optLog, HttpServletRequest request, ProceedingJoinPoint joinPoint) {
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
//        OptLog annotation = method.getAnnotation(OptLog.class);
        Operation apiOperation = method.getAnnotation(Operation.class);

        OperationLog log = new OperationLog();
        // 操作模块
        log.setOptModule(apiOperation.tags()[0]);
        // 操作类型
        log.setOptType(optLog.type().toString());
        // 操作描述
        log.setOptDesc(apiOperation.description());
        // 请求方式
        log.setRequestMethod(Objects.requireNonNull(request).getMethod());
        // 请求地址
        log.setOptUrl(request.getRequestURI());
        // 请求参数
        log.setRequestParam(JSON.toJSONString(joinPoint.getArgs()));
        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        String methodName = method.getName();
        methodName = className + "." + methodName;
        // 请求方法
        log.setOptMethod(methodName);
        // 请求IP
        String ipAddress = IpUtils.getIpAddress(request);
        log.setIpAddress(ipAddress);
        log.setIpSource(IpUtils.getIpSource(ipAddress));
        // 执行时间
        log.setCreateTime(LocalDateTime.now());
        // TODO 获取真实用户数据
        log.setUserId(1);
        log.setNickname("Fiee");
        return log;
    }
}

