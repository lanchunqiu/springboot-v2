package com.springboot.chapter9.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/1
 **/
@SpringBootApplication(scanBasePackages = "com.springboot.chapter9")
@MapperScan(basePackages = "com.springboot.chapter9", annotationClass = Repository.class)
public class Chapter9Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter9Application.class, args);
    }
}
