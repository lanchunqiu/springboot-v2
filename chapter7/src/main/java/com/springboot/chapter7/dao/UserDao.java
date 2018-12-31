package com.springboot.chapter7.dao;

import com.springboot.chapter7.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/31
 **/
@Repository
public interface UserDao {
    User getUser(Long id);

    int insertUser(User user);

    int updateUser(User user);

    List<User> findUsers(@Param("userName") String userName,
                         @Param("note") String note);

    int deleteUser(Long id);
}
