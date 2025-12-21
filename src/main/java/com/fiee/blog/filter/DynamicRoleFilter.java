package com.fiee.blog.filter;

import com.fiee.blog.dto.ResourceRoleDTO;
import com.fiee.blog.exception.BizException;
import com.fiee.blog.mapper.RoleMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Fiee
 * @ClassName: DynamicRoleFilter 动态角色过滤器
 * @Date: 2025/12/21
 * @Version: v1.0.0
 **/
//@Component
public class DynamicRoleFilter extends OncePerRequestFilter {
//    @Autowired
//    private RoleMapper roleMapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 获取用户请求的URI
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/doc/api/") || requestURI.equals("/api/v1/auth/login") || requestURI.equals("/favicon.ico")) {
            filterChain.doFilter(request, response);
            return;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        boolean authenticated = authentication.isAuthenticated();
        // 获取用户请求的方法
        String method = request.getMethod();
        // 获取所有资源角色信息
//        List<ResourceRoleDTO> resourceRoleDTO = roleMapper.listResourceRoles();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
//        for (ResourceRoleDTO dto : resourceRoleDTO){
//            if (antPathMatcher.match(dto.getUrl(),requestURI) &&
//                    method.equals(dto.getRequestMethod())){
//                if (dto.getIsAnonymous()==1){
//                    filterChain.doFilter(request,response);
//                }else{
//                    List<String> roleList = dto.getRoleList();
//                    for (String role : roleList){
//                        if (authentication.getAuthorities().stream().anyMatch(
//                                grantedAuthority -> grantedAuthority.getAuthority().equals(role))){
//                            filterChain.doFilter(request,response);
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println(resourceRoleDTO);
        System.out.println(requestURI);
//        System.out.println("authenticated = " + authenticated);
        System.out.println(authentication);
//        throw new BizException("权限不足");
    }
}
