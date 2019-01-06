package com.springboot.chapter10.controller.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/5
 **/
@ControllerAdvice(basePackages = {"com.springboot.chapter10.controller.*"},
annotations = Controller.class)
public class MyControllerAdvice {
    /**
     * 绑定格式化，参数转换规则和增加验证器等
     * @param binder
     */
    @InitBinder
    public void initDatader(WebDataBinder binder){
        //自定义日期标记器，限定格式为yyyy-MM-dd，且参数不允许为空
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false);
        //注册自定义编辑器
        binder.registerCustomEditor(Date.class, dateEditor);
    }


    /**
     * 在执行控制器之前先执行，可以初始化数据模型
     * @param model
     */
    @ModelAttribute
    public void projectModel(Model model) {

        model.addAttribute("project_name", "chapter10");
    }


    /**
     * 异常处理，使得被拦截的控制器方法发生异常时，都能用相同的视图响应
     * @param model
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public String exception(Model model, Exception ex) {
        // 给数据模型增加异常消息
        model.addAttribute("exception_message", ex.getMessage());
        // 返回异常视图
        return "exception";
    }
}
