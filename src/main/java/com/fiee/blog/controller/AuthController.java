package com.fiee.blog.controller;
import com.fiee.blog.annotation.AccessLimit;
import com.fiee.blog.dto.UserDetailDTO;
import com.fiee.blog.entity.UserAuth;
import com.fiee.blog.service.impl.UserDetailsServiceImpl;
import com.fiee.blog.utils.JwtUtils;
import com.fiee.blog.vo.ConditionVO;
import com.fiee.blog.vo.PageResult;
import com.fiee.blog.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Fiee
 * @ClassName: AuthController
 * @Date: 2025/12/20
 * @Version: v1.0.0
 **/
@RestController
@RequestMapping ("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Operation(summary = "后台用户登录",description = "后台用户登录操作",
            method = "POST",tags = "用户认证模块")
    @AccessLimit(seconds = 60,maxCount = 100)
    @PostMapping("/login")
    public Result login(@RequestParam String username,@RequestParam String password){
        return Result.ok(true);
    }

    @PostMapping("/phone/login")
    public void phoneLogin(@RequestParam String username,@RequestParam String password){

    }
}
