package com.springboot.chapter16.controller;

import com.springboot.chapter16.pojo.User;
import com.springboot.chapter16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService = null;

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
