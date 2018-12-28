package com.springboot.chapter5.controller;

import com.springboot.chapter5.dao.JpaUserRepository;
import com.springboot.chapter5.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/28
 **/
@Controller
@RequestMapping("/jpa")
public class JpaController {
    //注入JPa接口，这里不需要使用实现类
    @Autowired
    private JpaUserRepository jpaUserRepository = null;

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Long id){
        // 使用JPA接口查询对象
        User user = jpaUserRepository.findById(id).get();
        return user;
    }

    @RequestMapping("/findByUserNameLike")
    @ResponseBody
    public List<User> findByUserNameLike(String userName){
        List<User> userList = jpaUserRepository.findByUserNameLike("%" + userName +"%");
        return userList;
    }

    @RequestMapping("/findByUserNameLikeOrNoteLike")
    @ResponseBody
    public List<User> findByUserNameLikeOrNoteLike(String userName,String note){
        List<User> userList = jpaUserRepository.findByUserNameLikeOrNoteLike("%" + userName + "%", "%" + note + "%");
        return userList;
    }
}
