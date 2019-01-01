package com.springboot.chapter10.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/1
 **/
@Controller
@RequestMapping("/my")
public class MyController {

    /**
     * 在无注解下获取参数，请求参数名称和HTTP请求参数名称一致
     * @param intVal
     * @param longVal
     * @param str
     * @return
     */
    @GetMapping("/no/annotation")
    @ResponseBody
    public Map<String, Object> noAnnotation(Integer intVal, Long longVal, String str){
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("intVal", intVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("str", str);
        return paramsMap;
    }

    /**
     * 通过注解@RequestParam获取参数
     * @param intVal
     * @param longVal
     * @param str
     * @return
     */
    @GetMapping("/annotation")
    @ResponseBody
    public Map<String, Object> annotation(@RequestParam("int_val")Integer intVal,
                                          @RequestParam("long_val")Long longVal,
                                          @RequestParam(value = "str_val",required = false)String str){
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("intVal", intVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("str", str);
        return paramsMap;
    }

    @GetMapping("/requestArray")
    @ResponseBody
    public Map<String, Object> requestArray(int[] intArr,
                                          long[] longArr,
                                          String[] strArr){
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("intArr", intArr);
        paramsMap.put("longArr", longArr);
        paramsMap.put("strArr", strArr);
        return paramsMap;
    }

    // 映射JSP页面
    @GetMapping("/format/form")
    public String showFormat() {
        return "/format/formatter";
    }

    // 获取提交参数
    @PostMapping("/format/commit")
    @ResponseBody
    public Map<String, Object> format(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @NumberFormat(pattern = "#,###.##") Double number) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("date", date);
        dataMap.put("number", number);
        return dataMap;
    }
}
