package com.springboot.chapter16.service.impl;

import com.springboot.chapter16.dao.UserDao;
import com.springboot.chapter16.pojo.User;
import com.springboot.chapter16.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao = null;

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        return userDao.findUsers(userName, note);
    }
}
