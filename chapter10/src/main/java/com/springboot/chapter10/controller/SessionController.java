package com.springboot.chapter10.controller;

import com.springboot.chapter10.pojo.User;
import com.springboot.chapter10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/5
 **/
@Controller
@RequestMapping("/session")
//注解@SessionAttributes的作用是指定数据模型名称或者属性类型，保存到Session中
@SessionAttributes(names = {"user"}, types = Long.class)
public class SessionController {

    @Autowired
    private UserService userService = null;

    /**
     * 跳转到session页面
     * @return
     */
    @GetMapping("/page")
    public String headerPage() {
        return "session";
    }
    @GetMapping("/test")
    public String test(@SessionAttribute("id") Long id, //@SessionAttribute从HttpSession中取出数据，填充控制器方法参数
                       Model model){
        System.out.println("id = [" + id + "], model = [" + model + "]");
        model.addAttribute("id_new", id);
        //根据类型保存到Session中
        User user = userService.getUser(id);
        //根据名称保存到Session中
        model.addAttribute("user", user);
        return "session/test";
    }



}
