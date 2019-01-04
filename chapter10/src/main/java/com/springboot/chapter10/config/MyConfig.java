package com.springboot.chapter10.config;

import com.springboot.chapter10.interceptor.Interceptor1;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/4
 **/
@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器到SpringMVC机制，然后会返回一个拦截器注册
        InterceptorRegistration registration = registry.addInterceptor(new Interceptor1());
        //指定拦截匹配模式，限制拦截请求
        registration.addPathPatterns("/interceptor/*");
    }
}
