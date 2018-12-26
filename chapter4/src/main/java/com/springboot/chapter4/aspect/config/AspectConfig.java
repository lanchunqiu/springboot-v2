package com.springboot.chapter4.aspect.config;

import com.springboot.chapter4.aspect.MyAspect;
import com.springboot.chapter4.aspect.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/26
 **/
@Configuration
//启动@AspectJ切面编程
@EnableAspectJAutoProxy
//定义扫描包
@ComponentScan(basePackages = {"com.springboot.chapter4.aspect.*"}, basePackageClasses = UserServiceImpl.class)
public class AspectConfig {
    @Bean(name = "myAspect")
    public MyAspect initMyAspect() {
        return new MyAspect();
    }
}
