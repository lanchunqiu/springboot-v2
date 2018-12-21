package com.springboot.chapter3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/21
 **/
//@Configuration代表这是一个Java配置文件，Spring的容器会根据它来生成IoC容器取装配Bean
@Configuration
@ComponentScan(basePackages = "com.springboot.chapter3.*", excludeFilters = {@ComponentScan.Filter(classes={Service.class})})//标明采用何种策略去扫描装配Bean
public class AppConfig2 {
}
