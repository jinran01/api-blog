package com.fiee.blog.handler;

import com.alibaba.fastjson.JSON;
import com.fiee.blog.enums.StatusCodeEnum;
import com.fiee.blog.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.fiee.blog.constant.CommonConst.APPLICATION_JSON;

/**
 * @Author: Fiee
 * @ClassName: AuthenticationEntryPointImpl
 * @Date: 2025/12/21
 * @Version: v1.0.0
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(APPLICATION_JSON);
        response.getWriter().write(JSON.toJSONString(Result.fail(StatusCodeEnum.NO_LOGIN)));
    }
}
