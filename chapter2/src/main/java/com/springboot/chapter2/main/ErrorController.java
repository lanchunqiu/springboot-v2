package com.springboot.chapter2.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/21
 **/
@RequestMapping("/")
@RestController
public class ErrorController {
    @GetMapping("404")
    public String e404() {
        System.out.println("404............");
        return "这真的是一个404页面，你看看";
    }
}
