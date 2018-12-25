package com.springboot.chapter3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/22
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.springboot.chapter3.*")
@PropertySource(value={"classpath:jdbc.properties"}, ignoreResourceNotFound = true)
public class Chapter3Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter3Application.class, args);
    }
}
