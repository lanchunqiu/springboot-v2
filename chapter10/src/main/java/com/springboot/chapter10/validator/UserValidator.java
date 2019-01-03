package com.springboot.chapter10.validator;

import com.springboot.chapter10.pojo.User;
import org.apache.tools.ant.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/3
 **/
public class UserValidator implements Validator {
    /**
     * 该验证器支持User类验证
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(aClass);
    }

    /**
     * 验证逻辑
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        //对象为空
        if(target == null){
            //直接在参数处保存，这样就不能进入控制器的方法
            errors.rejectValue("", null, "用户不能为空");
            return;
        }
        //强制转换
        User user = (User) target;
        //用户非空
        if(user.getUserName() == null || "".equals(user.getUserName())){
            //增加错误，可以进入控制器方法
            errors.rejectValue("userName", null, "用户名不能为空");
        }
    }
}
