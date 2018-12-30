package com.springboot.chapter6.service.impl;

import com.springboot.chapter6.dao.UserDao;
import com.springboot.chapter6.pojo.User;
import com.springboot.chapter6.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/30
 **/
@Service
public class UserServiceImpl implements UserService, ApplicationContextAware {

    @Autowired
    private UserDao userDao = null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1, propagation = Propagation.REQUIRES_NEW)
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

//    /**
//     * 演示自调用失败的问题
//     * @param userList
//     * @return
//     */
//    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED,
//            propagation = Propagation.REQUIRED
//    )
//    public int insertUsers(List<User> userList) {
//        int count = 0;
//        for(User user : userList){
//            //调用自己类自身的方法，产生自调用的问题（传播行为失效）
//            count += insertUser(user);
//        }
//        return count;
//    }


    private ApplicationContext applicationContext = null;
    //实现声明周期方法，设置IOC容器
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    /**
     * 使用代理对象执行插入用户，克服自调用的问题
     * @param userList
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED
    )
    public int insertUsers(List<User> userList) {
        int count = 0;
        //从IOC容器中取出代理对象
        UserService userService = applicationContext.getBean(UserService.class);
        for(User user : userList){
            //调用代理对象方法插入用户，此时会织入spring数据库事务流程中
            count += userService.insertUser(user);
        }
        return count;
    }
}
