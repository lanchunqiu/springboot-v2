package com.springboot.chapter4.main;

import com.springboot.chapter4.aspect.MyAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/26
 **/
@SpringBootApplication(scanBasePackages = "com.springboot.chapter4.aspect")
public class Chapter4Application {

    //初始化切面
    @Bean(name = "myAspect")
    public MyAspect initMyAspect(){
        return new MyAspect();
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter4Application.class, args);
    }
}
