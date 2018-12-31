package com.springboot.chapter8.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/31
 **/
@SpringBootApplication(scanBasePackages = "com.springboot.chapter8")
//指定扫描包，用于扫描继承了MongoRepository的接口
@EnableMongoRepositories(
        basePackages = "com.springboot.chapter8.repository"
        //使用自定义后缀，其默认值为Impl
        //此时需要修改类名：UserRepositoryImpl-->UserRepositoryStuff
        ,repositoryImplementationPostfix ="Stuff")
public class Chapter8Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter8Application.class, args);
    }
}
