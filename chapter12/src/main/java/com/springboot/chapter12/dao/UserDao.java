package com.springboot.chapter12.dao;

import com.springboot.chapter12.pojo.DatabaseUser;
import org.springframework.stereotype.Repository;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/9
 **/
@Repository
public interface UserDao {
    public DatabaseUser getUser(String userName);
}
