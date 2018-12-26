package com.springboot.chapter4.aspect.validator;

import com.springboot.chapter4.pojo.User;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/26
 **/
public interface UserValidator {
    /**
     * 检测用户是否为空
     * @param user
     * @return
     */
    public boolean validate(User user);
}
