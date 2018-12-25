package com.springboot.chapter4.aspect.service;

import com.springboot.chapter4.pojo.User;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/25
 **/
public interface UserService {

    public void printUser();

    public void printUser(User user);

    public void manyAspects();
}
