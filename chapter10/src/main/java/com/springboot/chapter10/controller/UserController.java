package com.springboot.chapter10.controller;

import com.springboot.chapter10.pojo.User;
import com.springboot.chapter10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/1
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService = null;

    /**
     * 打开请求页面
     * @return
     */
    @GetMapping("/add")
    public String add(){
        return "/user/add";
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public User insert(@RequestBody User user){
        userService.insertUser(user);
        return user;
    }

    // {...}代表占位符，还可以配置参数名称
    @GetMapping("/{id}")
    // 响应为JSON数据集
    @ResponseBody
    // @PathVariable通过名称获取参数
    public User get(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }
}
