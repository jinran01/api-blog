package com.fiee.blog.exception;

import com.fiee.blog.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.fiee.blog.enums.StatusCodeEnum.FAIL;

/**
 * @Author: Fiee
 * @ClassName: BizException
 * @Date: 2025/12/20
 * @Version: v1.0.0
 **/
@Getter
@AllArgsConstructor
public class BizException extends RuntimeException{
    /**
     * 错误码
     */
    private Integer code = FAIL.getCode();

    /**
     * 错误信息
     */
    private final String message;

    public BizException(String message) {
        this.message = message;
    }

    public BizException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }
}
