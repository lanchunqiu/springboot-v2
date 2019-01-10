package com.springboot.chapter12.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/10
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        // 使得/login/page映射为longin.jsp
        registry.addViewController("/login/page").setViewName("login");

        //使得/logout/page映射为logout_welcome.jsp
        registry.addViewController("/logout/page").setViewName("logout_welcome");

        //使得/logout映射为logout.jsp
        registry.addViewController("/logout").setViewName("logout");
    }
}
