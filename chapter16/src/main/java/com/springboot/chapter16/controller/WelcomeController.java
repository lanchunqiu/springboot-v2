package com.springboot.chapter16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
@Controller
public class WelcomeController {
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/endpoint")
    public String endpoint() {
        return "endpoint";
    }

    @GetMapping(value="/test")
    @ResponseBody
    public Map<String, String> test() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value");
        return map;
    }
}
