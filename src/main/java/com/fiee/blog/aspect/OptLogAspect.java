package com.fiee.blog.aspect;

import com.fiee.blog.annotation.OptLog;
import com.fiee.blog.entity.OperationLog;
import com.fiee.blog.mapper.OperationLogMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;
import io.swagger.v3.oas.annotations.Operation;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.Method;
import java.util.Objects;


/**
 * @Author: Fiee
 * @ClassName: OptLogAspect 操作日志切面处理
 * @Date: 2025/12/17
 * @Version: v1.0.0
 **/
//@Aspect
//@Component
public class OptLogAspect {
    @Autowired
    private OperationLogMapper operationLogMapper;

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.fiee.blog.annotation.OptLog)")
    public void optLogPointCut() {}

    /**
     * 保存操作日志
     * @param joinPoint 切点
     * @param keys      返回结果
     */
    @AfterReturning(value = "optLogPointCut()", returning = "keys")
    public void saveOptLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes).resolveReference(RequestAttributes.REFERENCE_REQUEST);
        OperationLog operationLog = new OperationLog();
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作
//        Api api = (Api) signature.getDeclaringType().getAnnotation(Api.class);
//        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
//        ApiDescription api = (ApiDescription) signature.getDeclaringType().getAnnotation(ApiDescription.class);
        Operation apiOperation = method.getAnnotation(Operation.class);
        OptLog optLog = method.getAnnotation(OptLog.class);
        // 操作模块
        operationLog.setOptModule(apiOperation.tags()[0]);
//        // 操作类型
        operationLog.setOptType(optLog.type().toString());
//        // 操作描述
        operationLog.setOptDesc(apiOperation.description());
        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        String methodName = method.getName();
        methodName = className + "." + methodName;

        // 请求方式
        operationLog.setRequestMethod(Objects.requireNonNull(request).getMethod());
        // 请求方法
        operationLog.setOptMethod(methodName);
        System.out.println(operationLog);
        // 请求参数
//        operationLog.setRequestParam(JSON.toJSONString(joinPoint.getArgs()));
//        // 返回结果
//        operationLog.setResponseData(JSON.toJSONString(keys));
//        // 请求用户ID
//        operationLog.setUserId(UserUtils.getLoginUser().getId());
//        // 请求用户
//        operationLog.setNickname(UserUtils.getLoginUser().getNickname());
//        // 请求IP
//        String ipAddress = IpUtils.getIpAddress(request);
//        operationLog.setIpAddress(ipAddress);
//        operationLog.setIpSource(IpUtils.getIpSource(ipAddress));
//        // 请求URL
//        operationLog.setOptUrl(request.getRequestURI());
//        operationLogDao.insert(operationLog);
    }
    public OperationLog buildLogEntity(HttpServletRequest request, Method method, OptLog optLog) {
        return null;
    }
}
