package com.springboot.chapter10.service;

import com.springboot.chapter10.pojo.User;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/1
 **/
public interface UserService {

    User getUser(Long id);

    List<User> findUsers(String userName, String note);

    int insertUser(User user);
}
