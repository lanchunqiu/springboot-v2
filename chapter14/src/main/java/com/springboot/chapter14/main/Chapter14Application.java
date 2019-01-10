package com.springboot.chapter14.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/11
 **/
// 定义扫描包
@SpringBootApplication(scanBasePackages = "com.springboot.chapter14")
// 因为引入JPA，所以默认情况下，需要配置数据源
// 通过@@EnableAutoConfiguration排除原有的数据源
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// 在WebFlux下，驱动MongoDB的JPA接口
@EnableReactiveMongoRepositories(
        // 定义扫描的包
        basePackages = "com.springboot.chapter14.repository")
public class Chapter14Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter14Application.class, args);
    }
}
