package com.springboot.chapter16.service;

import com.springboot.chapter16.pojo.User;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
public interface UserService {
    User getUser(Long id);

    List<User> findUsers(String userName, String note);
}
