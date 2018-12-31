package com.springboot.chapter8.repository;

import com.springboot.chapter8.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/31
 **/
//标识为DAO层
@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    /**
     * 符合JPA规范命名方法，则不需要再实现该方法
     * @param userName
     * @return
     */
    List<User> findByUserNameLike(String userName);

    /**
     * 根据编号或者用户名查找用户
     * @param id -- 编号
     * @param userName -- 用户名
     * @return 用户信息
     */
    User findUserByIdOrUserName(Long id, String userName);
}
