package com.springboot.chapter6.controller;

import com.springboot.chapter6.pojo.User;
import com.springboot.chapter6.service.UserBatchService;
import com.springboot.chapter6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/30
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    //注入Service
    @Autowired
    private UserService userService = null;
    @Autowired
    private UserBatchService userBatchService = null;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id){
        return userService.getUser(id);
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public Map<String, Object> insertUser(String userName, String note){
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        //结果回填主键，返回插入条数
        int update = userService.insertUser(user);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", update == 1);
        result.put("user", user);
        return result;
    }

    @RequestMapping("/insertUsers")
    @ResponseBody
    public Map<String, Object> insertUsers(String userName1, String note1, String userName2, String note2){
        User user1 = new User();
        user1.setUserName(userName1);
        user1.setNote(note1);
        User user2 = new User();
        user2.setUserName(userName2);
        user2.setNote(note2);
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);

        //结果回填主键，返回插入条数
        //int update = userBatchService.insertUsers(userList);
        int update = userService.insertUsers(userList);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", update == 2);
        result.put("user", userList);
        return result;
    }
}
