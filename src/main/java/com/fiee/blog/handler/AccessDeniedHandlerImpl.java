package com.fiee.blog.handler;

import com.alibaba.fastjson.JSON;
import com.fiee.blog.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.fiee.blog.constant.CommonConst.APPLICATION_JSON;

/**
 * @Author: Fiee
 * @ClassName: AccessDeniedHandlerImpl
 * @Date: 2025/12/21
 * @Version: v1.0.0
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON);
        response.getWriter().write(JSON.toJSONString(Result.fail("权限不足")));
    }
}
