package com.fiee.blog.annotation;

import com.fiee.blog.enums.LogType;
import java.lang.annotation.*;

/**
 * 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    String value() default "";  // 操作描述

    LogType type() default LogType.QUERY;  // 操作类型（查询/新增/修改/删除）

}

