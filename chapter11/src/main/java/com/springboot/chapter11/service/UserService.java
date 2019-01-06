package com.springboot.chapter11.service;

import com.springboot.chapter11.pojo.User;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/6
 **/
public interface UserService {

    public User insertUser(User user);

    public User getUser(Long id);

    public List<User> findUsers(String userName, String note, int start, int limit);

    public int updateUser(User user);

    public int  updateUserName(Long id, String userName);

    public int deleteUser(Long id);
}
