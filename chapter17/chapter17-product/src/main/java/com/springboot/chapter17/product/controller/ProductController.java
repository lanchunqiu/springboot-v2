package com.springboot.chapter17.product.controller;

import com.springboot.chapter17.product.service.UserService;
import com.springboot.chapter17.user.pojo.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
@RestController
@RequestMapping("/product")
public class ProductController {
    // 注入RestTemplate
    @Autowired
    private RestTemplate restTemplate = null;

    @GetMapping("/ribbon")
    public UserPo testRibbon(){
        UserPo user = null;
        // 循环10次，然后可以看到各个用户微服务后台的日志打印
        for (int i = 0; i < 10; i++) {
            // 注意这里直接使用了USER这个服务ID，代表用户微服务系统
            // 该ID通过属性spring.application.name来指定
            user = restTemplate.getForObject("http://USER/user/" + (i + 1), UserPo.class);
        }
        return user;
    }

    // 注入Feign接口
    @Autowired
    private UserService userService = null;

    // 测试
    @GetMapping("/feign")
    public UserPo testFeign() {
        UserPo user = null;
        // 循环10次
        for (int i = 0; i < 10; i++) {
            Long id = (long) (i + 1);
            user = userService.getUser(id);
        }
        return user;
    }

    @GetMapping("/feign2")
    public Map<String, Object> testFeign2() {
        Map<String, Object> result = null;
        UserPo user = null;
        for (int i = 1; i <= 10; i++) {
            Long id = (long) i;
            user = new UserPo();
            user.setId(id);
            int level = i % 3 + 1;
            user.setUserName("user_name_" + id);
            user.setLevel(level);
            user.setNote("note_" + i);
            result = userService.addUser(user);
        }
        return result;
    }

    @GetMapping("/feign3")
    public Map<String, Object> testFeign3() {
        Map<String, Object> result = null;
        for (int i = 0; i < 10; i++) {
            Long id = (long) (i + 1);
            String userName = "user_name_" + id;
            result = userService.updateName(userName, id);
        }
        return result;
    }
}
