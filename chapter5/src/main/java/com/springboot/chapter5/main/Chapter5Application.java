package com.springboot.chapter5.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/28
 **/
//定义Spring Boot扫描包路径
@SpringBootApplication(scanBasePackages = "com.springboot.chapter5")
//定义JPA接口扫描包路径
@EnableJpaRepositories(basePackages = "com.springboot.chapter5.dao")
//定义实体Bean扫描包路径
@EntityScan(basePackages = "com.springboot.chapter5.pojo")
public class Chapter5Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter5Application.class, args);
    }
}
