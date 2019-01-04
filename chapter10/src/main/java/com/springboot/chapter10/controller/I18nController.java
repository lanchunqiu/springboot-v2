package com.springboot.chapter10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/4
 **/
@Controller
@RequestMapping("/i18n")
public class I18nController {
    //注入国际化消息接口对象
    @Autowired
    private MessageSource messageSource;

    //后天获取国际化信息和贷款国际化视图
    @GetMapping("/page")
    public ModelAndView page(String language,ModelMap modelMap){
        Locale locale = LocaleContextHolder.getLocale();
        //获取国际化消息
        String msg = messageSource.getMessage("msg", null, locale);
        System.out.println("msg" + msg);

        modelMap.put("locale",locale.getDisplayName());

        ModelAndView mv = new ModelAndView();
        mv.setViewName("i18n/internationalization");
        //返回视图
        return mv;
    }
}
