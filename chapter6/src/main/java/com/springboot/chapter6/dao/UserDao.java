package com.springboot.chapter6.dao;

import com.springboot.chapter6.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/30
 **/
@Repository//@Repository注解可以标记在任何的类上，用来表明该类是用来执行与数据库相关的操作（即dao对象），并支持自动处理数据库操作产生的异常
public interface UserDao {
    User getUser(Long id);
    int insertUser(User user);
}
