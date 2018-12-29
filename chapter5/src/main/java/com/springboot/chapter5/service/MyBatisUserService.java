package com.springboot.chapter5.service;

import com.springboot.chapter5.pojo.User;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/29
 **/
public interface MyBatisUserService {
    public User getUser(Long id);
}
