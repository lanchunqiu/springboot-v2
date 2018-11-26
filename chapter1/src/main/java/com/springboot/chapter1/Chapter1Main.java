package com.springboot.chapter1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lancq
 * @Description
 * @Date 2018/11/25
 **/
@Controller
@EnableAutoConfiguration
public class Chapter1Main {
    @RequestMapping("/test")
    @ResponseBody
    public Map<String,String> test(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("key","value");
        return map;
    }

    public static void main(String[] args) {
        //SpringApplication.run(Chapter1Main.class,args);
        SpringApplication springApplication = new SpringApplication(Chapter1Main.class);
        Map<String, Object> properties = new HashMap<>();
        properties.put("server.port", 5555);
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
    }
}
