package com.springboot.chapter11.dao;

import com.springboot.chapter11.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/6
 **/
@Repository
public interface UserDao {
    public User getUser(Long id);

    public int insertUser(User user);

    public List<User> findUsers(@Param("userName") String userName, @Param("note") String note, @Param("start") int start, @Param("limit") int limit);

    public int updateUser(User user);

    public int updateUserName(@Param("id") Long id, @Param("userName") String userName);

    public int deleteUser(Long id);
}
