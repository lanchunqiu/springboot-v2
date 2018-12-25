package com.springboot.chapter3.service;

import com.springboot.chapter3.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/21
 **/
@Service
public class UserService {
    private static Logger log = Logger.getLogger(UserService.class);

    public void printUser(User user){
        log.info("编号：" + user.getId());
        log.info("用户名：" + user.getId());
        log.info("备注：" + user.getId());
    }
}
