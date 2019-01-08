package com.springboot.chapter11.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/8
 **/
@ControllerAdvice(
        basePackages = {"com.springboot.chapter11.controller.*"},
        annotations = {Controller.class, RestController.class}
)
public class VoControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class)//异常处理，可以异常类型进行拦截处理
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//定义服务器错误状态码
    public Map<String,Object> exception(HttpServletRequest request, NotFoundException ex){
        Map<String,Object> msgMap = new HashMap<String,Object>();
        //获取异常信息
        msgMap.put("code", ex.getCode());
        msgMap.put("message", ex.getCustomMsg());
        return msgMap;
    }
}
