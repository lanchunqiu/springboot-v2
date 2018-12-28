package com.springboot.chapter5.dao;

import com.springboot.chapter5.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/28
 **/
public interface JpaUserRepository extends JpaRepository<User,Long> {

    List<User> findByUserNameLike(String userName);

    List<User> findByUserNameLikeOrNoteLike(String userName, String note);
}
