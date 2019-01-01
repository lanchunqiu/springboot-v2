package com.springboot.chapter9.controller;

import com.springboot.chapter9.pojo.User;
import com.springboot.chapter9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/1
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService = null;

    @RequestMapping("details")
    public ModelAndView details(Long id){
        User user = userService.getUser(id);

        ModelAndView mv = new ModelAndView();

        mv.setViewName("user/details");

        mv.addObject("user", user);

        return mv;
    }


    @RequestMapping("/detailsForJson")
    public ModelAndView detailsForJson(Long id) {
        // 访问模型层得到数据
        User user = userService.getUser(id);
        // 模型和视图
        ModelAndView mv = new ModelAndView();
        // 生成JSON视图
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        mv.setView(jsonView);
        // 加入模型
        mv.addObject("user", user);
        return mv;
    }

    @RequestMapping("/table")
    public ModelAndView table() {
        // 访问模型层得到数据
        List<User> userList = userService.findUsers(null, null);
        // 模型和视图
        ModelAndView mv = new ModelAndView();
        // 定义模型视图
        mv.setViewName("user/table");
        // 加入数据模型
        mv.addObject("userList", userList);
        // 返回模型和视图
        return mv;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(@RequestParam(value = "userName", required = false) String userName,
                           @RequestParam(value = "note", required = false) String note) {
        // 访问模型层得到数据
        List<User> userList = userService.findUsers(userName, note);
        return userList;
    }
}
