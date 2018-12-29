package com.springboot.chapter5.dao;

import com.springboot.chapter5.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/29
 **/
@Repository
public interface MyBatisUserDao {
    public User getUser(Long id);
}
