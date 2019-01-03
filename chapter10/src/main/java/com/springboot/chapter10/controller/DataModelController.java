package com.springboot.chapter10.controller;

import com.springboot.chapter10.pojo.User;
import com.springboot.chapter10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/3
 **/
@RequestMapping("/data")
@Controller
public class DataModelController {
    @Autowired
    private UserService userService = null;

    /**
     * 测试model
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/model")
    public String userModel(Long id, Model model){
        User user = userService.getUser(id);
        model.addAttribute("user",user);

        //这里返回字符串，在SpringMVC中，会自动创建ModelAndView且绑定名称
        return "data/user";
    }

    /**
     * 测试modelMap
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/modelMap")
    public ModelAndView userModelMap(Long id, ModelMap modelMap){
        User user = userService.getUser(id);
        ModelAndView mv = new ModelAndView();
        //设置视图名称
        mv.setViewName("data/user");
        //设置数据模型，此处modelMap并没有与MV绑定，这步系统会自动处理
        modelMap.put("user", user);
        return mv;
    }

    /**
     * 测试modelMap
     * @param id
     * @param mv
     * @return
     */
    @GetMapping("/mav")
    public ModelAndView userModelAndView(Long id, ModelAndView mv){
        User user = userService.getUser(id);
        //设置视图名称
        mv.setViewName("data/user");
        //设置数据模型
        mv.addObject("user", user);
        return mv;
    }
}
