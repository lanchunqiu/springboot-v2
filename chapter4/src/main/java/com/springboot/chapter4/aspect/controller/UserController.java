package com.springboot.chapter4.aspect.controller;

import com.springboot.chapter4.aspect.service.UserService;
import com.springboot.chapter4.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/26
 **/
// 定义控制器
@Controller
// 定义类请求路径
@RequestMapping("/user")
public class UserController {

    // 注入用户服务
    @Autowired
    private UserService userService = null;

    // 定义请求
    @RequestMapping("/print")
    //转换为JSON
    @ResponseBody
    public User pringtUser(Long id, String userName, String note){
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setNote(note);
        userService.printUser(user);
        return user;
    }
}
