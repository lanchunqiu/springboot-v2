package com.springboot.chapter6.service;

import com.springboot.chapter6.pojo.User;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/30
 **/
public interface UserBatchService {

    public int insertUsers(List<User> userList);
}
