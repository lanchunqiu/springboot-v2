package com.springboot.chapter7.service.impl;

import com.springboot.chapter7.dao.UserDao;
import com.springboot.chapter7.pojo.User;
import com.springboot.chapter7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/31
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao = null;

    @Override
    @Transactional
    //注解@Cacheable表示先从缓存中通过定义的键查询数据，如果可以查询到，则返回，否则执行该方法返回数据，并且将返回结果存放到缓存中
    @Cacheable(value = "redisCache",key = "'redis_user_' + #id")
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    //注解@CachePut表示将方法的返回结果存放到缓存中
    @CachePut(value = "redisCache", key = "'redis_user_' + #result.id")
    public User insertUser(User user) {
        userDao.insertUser(user);
        return user;
    }

    /**
     * 更新数据后，更新缓存，如果condition配置项使结果返回null，不缓存
     * @param id
     * @param userName
     * @return
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache", condition = "#result != 'null'", key = "'redis_user_' + #id")
    public User updateUserName(Long id, String userName) {
        //此处调用getUser方法，该方法缓存注解失效，所以这里还会执行SQL，将查询到数据库最新的数据
        User user = this.getUser(id);
        if(user == null){
            return null;
        }
        user.setUserName(userName);
        userDao.updateUser(user);
        return user;
    }

    /**
     * 命中率低，所以不采用缓存机制
     * @param userName
     * @param note
     * @return
     */
    @Override
    @Transactional
    public List<User> findUsers(String userName, String note) {
        return userDao.findUsers(userName, note);
    }

    /**
     * 移除缓存
     * @param id
     * @return
     */
    @Override
    @Transactional
    //注解@CacheEvict表示通过定义的键移除缓存，它有一个Boolean类型的配置项beforeInvocation，表示在方法之前或之后移除缓存。因为其默认值为false,所以默认为方法之后移除缓存。
    @CacheEvict(value = "redisCache", key = "'redis_user_'+#id", beforeInvocation = false)
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
}
