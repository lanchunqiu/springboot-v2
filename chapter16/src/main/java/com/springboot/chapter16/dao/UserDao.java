package com.springboot.chapter16.dao;

import com.springboot.chapter16.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
@Mapper
public interface UserDao {

    User getUser(Long id);

    List<User> findUsers(@Param("userName") String userName, @Param("note") String note);
}
