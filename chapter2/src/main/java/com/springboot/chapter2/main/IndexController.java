package com.springboot.chapter2.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/19
 **/
@Controller("/")
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
