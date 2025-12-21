package com.fiee.blog.handler;

import com.alibaba.fastjson.JSON;
import com.fiee.blog.dto.UserInfoDTO;
import com.fiee.blog.utils.BeanCopyUtils;
import com.fiee.blog.utils.UserUtils;
import com.fiee.blog.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.fiee.blog.constant.CommonConst.APPLICATION_JSON;

/**
 * @Author: Fiee
 * @ClassName: AuthenticationSuccessHandlerImpl
 * @Date: 2025/12/21
 * @Version: v1.0.0
 **/
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 返回登录信息
        UserInfoDTO userLoginDTO = BeanCopyUtils.copyObject(UserUtils.getLoginUser(), UserInfoDTO.class);
        response.setContentType(APPLICATION_JSON);
        response.getWriter().write(JSON.toJSONString(Result.ok(userLoginDTO)));
    }
}
