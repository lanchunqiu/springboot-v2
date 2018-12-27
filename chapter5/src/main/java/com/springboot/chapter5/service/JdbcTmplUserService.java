package com.springboot.chapter5.service;

import com.springboot.chapter5.pojo.User;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/27
 **/
public interface JdbcTmplUserService {

    public User getUser(Long id);

    public List<User> findUsers(String userName, String note);

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUser(Long id);
}
