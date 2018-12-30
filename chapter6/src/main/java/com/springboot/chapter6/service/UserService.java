package com.springboot.chapter6.service;

import com.springboot.chapter6.pojo.User;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/30
 **/
public interface UserService {
    // 获取用户信息
    public User getUser(Long id);

    // 新增用户
    public int insertUser(User user);

    public int insertUsers(List<User> userList);
}
