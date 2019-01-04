package com.springboot.chapter10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/4
 **/
@RequestMapping("/file")
@Controller
public class FileController {

    /**
     * 打卡文件上传界面
     * @return
     */
    @GetMapping("/upload/page")
    public String uploadPage(){
        return "file/upload";
    }

    /**
     * 使用HttpServletRequest作为参数
     * @param request
     * @return
     */
    @PostMapping("/upload/request")
    @ResponseBody
    public Map<String,Object> uploadRequest(HttpServletRequest request){
        boolean falg = false;
        MultipartHttpServletRequest mreq = null;
        if(request instanceof MultipartHttpServletRequest){
            mreq = (MultipartHttpServletRequest) request;
        } else {
            return dealResultMap(false,"上传失败");
        }

        //获取MultipartFile文件信息
        MultipartFile mf = mreq.getFile("file");
        //获取源文件名称
        String fileName = mf.getOriginalFilename();
        File file = new File(fileName);
        try{
            //保存文件
            mf.transferTo(file);
        } catch (Exception e){
            e.printStackTrace();
            return dealResultMap(false,"上传失败");
        }
        return dealResultMap(true,"上传成功");
    }

    /**
     * 使用SpringMVC的MultipartFile作为参数
     * @param file
     * @return
     */
    @PostMapping("/upload/multipart")
    @ResponseBody
    public Map<String,Object> uploadMultipartFile(MultipartFile file){
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false,"上传失败");
        }
        return dealResultMap(true,"上传成功");
    }

    /**
     * 使用Servlet的Part作为参数
     * @param file
     * @return
     */
    @PostMapping("/upload/part")
    @ResponseBody
    public Map<String, Object> uploadPart(Part file){
        //获取提交文件名称
        String fileName = file.getSubmittedFileName();
        try {
            //写入文件
            file.write(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false,"上传失败");
        }
        return dealResultMap(true,"上传成功");
    }

    /**
     * 处理文件上传结果
     * @param success
     * @param msg
     * @return
     */
    private Map<String, Object> dealResultMap(boolean success, String msg){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }
}
