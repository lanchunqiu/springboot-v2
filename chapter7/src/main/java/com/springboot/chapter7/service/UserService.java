package com.springboot.chapter7.service;

import com.springboot.chapter7.pojo.User;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/31
 **/
public interface UserService {

    User getUser(Long id);

    User insertUser(User user);

    User updateUserName(Long id, String userName);

    List<User> findUsers(String userName, String note);

    int deleteUser(Long id);
}
