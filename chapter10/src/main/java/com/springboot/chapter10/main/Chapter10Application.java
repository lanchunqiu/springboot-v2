package com.springboot.chapter10.main;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/1
 **/
@SpringBootApplication(scanBasePackages = "com.springboot.chapter10")
@MapperScan(basePackages = "com.springboot.chapter10", annotationClass = Mapper.class)
public class Chapter10Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter10Application.class, args);

    }
}
