package com.springboot.chapter17.user.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
@SpringBootApplication(scanBasePackages="com.springboot.chapter17.user")
//自定义扫描包
@ComponentScan(basePackages = "com.springboot.chapter17.user")
public class Chapter17UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter17UserApplication.class, args);
    }

}
