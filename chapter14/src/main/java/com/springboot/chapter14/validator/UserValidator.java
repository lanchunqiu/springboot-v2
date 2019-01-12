package com.springboot.chapter14.validator;

import com.springboot.chapter14.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/12
 **/
public class UserValidator implements Validator {
    /**
     * 确定支持的验证类型
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    /**
     * 验证逻辑
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        //检查用户名是否为空
        if(StringUtils.isEmpty(user.getUserName())){
            errors.rejectValue("userName", null, "用户名不能为空");
        }
    }
}
