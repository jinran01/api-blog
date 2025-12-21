package com.fiee.blog.config;
import com.fiee.blog.filter.DynamicRoleFilter;
//import com.fiee.blog.filter.JwtAuthFilter;
import com.fiee.blog.handler.*;
import com.fiee.blog.service.impl.UserDetailsServiceImpl;
import com.fiee.blog.smsauthentication.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: Fiee
 * @ClassName: SpringSecurityConfig
 * @Date: 2025/12/20
 * @Version: v1.0.0
 **/
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
//    @Autowired
//    private JwtAuthFilter jwtAuthFilter;
//    @Autowired
//    private DynamicRoleFilter dynamicRoleFilter;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailHandlerImpl authenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Autowired
    private AccessDecisionManagerImpl accessDecisionManager;
    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl dynamicSecurityMetadataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AccessDeniedHandlerImpl accessDeniedHandlerImpl) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .permitAll()
                )
                .anonymous(anonymous -> anonymous.authorities("anonymous"))
                .formLogin(form -> form.loginProcessingUrl("/api/v1/auth/login")
                        .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler))
                .formLogin( form-> form.loginPage("/api/v1/auth/logintest")
                        .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )

                .exceptionHandling(exception ->
                        exception.accessDeniedHandler(accessDeniedHandler)
                                .authenticationEntryPoint(authenticationEntryPoint))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(dynamicFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean

    public FilterSecurityInterceptor dynamicFilterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
        interceptor.setSecurityMetadataSource(dynamicSecurityMetadataSource);
        interceptor.setAccessDecisionManager(accessDecisionManager);
        interceptor.setAuthenticationManager(authenticationManagerBean());
        return interceptor;
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(authenticationProvider(),new SmsAuthenticationProvider(userDetailsServiceImpl));
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
