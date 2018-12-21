package com.springboot.chapter3.config;

import com.springboot.chapter3.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/21
 **/
//@Configuration代表这是一个Java配置文件，Spring的容器会根据它来生成IoC容器取装配Bean
@Configuration
public class AppConfig {
    //@Bean表示将方法返回的POJO装配到IoC容器中，属性name定义这个bean的名称，如果没有配置将方法名称作为Bean的名称
    @Bean(name = "user")
    public User initUser(){
        User user = new User();
        user.setId(1L);
        user.setUserName("user_name_1");
        user.setNote("note_1");
        return user;
    }
}
