package com.springboot.chapter17.product.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
//@SpringBootApplication(scanBasePackages="com.springboot.chapter17.product")
@EnableFeignClients(basePackages = "com.springboot.chapter17.product")
//自定义扫描包
@ComponentScan(basePackages = "com.springboot.chapter17.product")
// 启动断路器
//@EnableCircuitBreaker

//开启SpringBoot应用，服务发现和断路器功能
@SpringCloudApplication//效果相当于三个注解@SpringBootApplication，@EnableDiscoveryClient，@EnableCircuitBreaker
public class Chapter17ProductApplication {

    // 初始化RestTemplate
    @LoadBalanced // 多节点负载均衡
    @Bean(name = "restTemplate")
    public RestTemplate initRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter17ProductApplication.class, args);
    }
}
