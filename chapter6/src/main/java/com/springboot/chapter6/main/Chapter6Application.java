package com.springboot.chapter6.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/30
 **/
@SpringBootApplication(scanBasePackages = "com.springboot.chapter6")
@MapperScan(
        basePackages = "com.springboot.chapter6",
        annotationClass = Repository.class
)
public class Chapter6Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter6Application.class, args);
    }

    //注入事务管理器，它由springboot自动生成
    @Autowired
    PlatformTransactionManager transactionManager = null;

    @PostConstruct
    public void viewTransactionManager(){
        System.out.println(transactionManager.getClass().getName());
    }
}
