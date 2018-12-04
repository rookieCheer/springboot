package com.lyf.springboot02web.config;

import com.lyf.springboot02web.interceptor.AuthorizationInterceptor;
import com.lyf.springboot02web.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    //在此处，将拦截器注册为一个 Bean
    @Bean
    public PermissionInterceptor pInterceptor() {
        return new PermissionInterceptor();
    }

    @Bean
    public AuthorizationInterceptor aInterceptor() {
        return new AuthorizationInterceptor();
    }


    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(aInterceptor())
                //用于添加拦截规则
                .addPathPatterns("/**")
                //用户排除拦截
                .excludePathPatterns("/login/**", "/logout/**", "/error/**","/loginForm/**");
        registry.addInterceptor(pInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**", "/logout/**", "/error/**","/loginForm/**");


    }
}
