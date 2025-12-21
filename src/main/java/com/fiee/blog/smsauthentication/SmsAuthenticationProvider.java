package com.fiee.blog.smsauthentication;

import com.fiee.blog.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Author: Fiee
 * @ClassName: SmsAuthenticationProvider
 * @Date: 2025/12/21
 * @Version: v1.0.0
 **/
@Component
public class SmsAuthenticationProvider implements AuthenticationProvider {
//    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public SmsAuthenticationProvider(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsService = userDetailsServiceImpl;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phone=(String) authentication.getPrincipal();//手机号
        String code=(String) authentication.getCredentials();//验证码
        //验证验证码是否正确 TODO

//        String username=phone;
        UserDetails userDetails =userDetailsService.loadUserByUsername(phone);

        return new SmsAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);

    }
}
