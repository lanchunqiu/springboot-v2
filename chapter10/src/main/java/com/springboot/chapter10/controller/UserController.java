package com.springboot.chapter10.controller;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.springboot.chapter10.pojo.User;
import com.springboot.chapter10.service.UserService;
import com.springboot.chapter10.validator.UserValidator;
import com.springboot.chapter10.view.PdfExportService;
import com.springboot.chapter10.view.PdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 打开请求页面
     * @return
     */
    @GetMapping("/add")
    public String add(){
        return "/user/add";
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public User insert(@RequestBody User user){
        userService.insertUser(user);
        return user;
    }

    // {...}代表占位符，还可以配置参数名称
    @GetMapping("/{id}")
    // 响应为JSON数据集
    @ResponseBody
    // @PathVariable通过名称获取参数
    public User get(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/converter")
    @ResponseBody
    public User getUserByConverter(User user){
        return user;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<User> list(List<User> userList){
        return userList;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        //绑定验证器
        binder.setValidator(new UserValidator());
        //定义日期参数格式，参数不在需要注解@DataTimeFormat,boolean 参数表示是否允许为空
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));
    }

    @GetMapping("/validator")
    @ResponseBody
    public Map<String, Object> validator(@Valid User user,
                                         Errors errors, Date date){
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("date", date);
        //判断是否存在错误
        if(errors.hasErrors()){
            List<ObjectError> oes = errors.getAllErrors();
            for(ObjectError oe : oes){
                //判断是否字段错误
                if(oe instanceof FieldError){
                    //字段错误
                    FieldError fe = (FieldError) oe;
                    map.put(fe.getField(), fe.getDefaultMessage());
                } else {
                    //对象错误
                    map.put(oe.getObjectName(), oe.getDefaultMessage());
                }
            }
        }
        return map;
    }

    @GetMapping("/export/pdf")
    public ModelAndView exportPdf(String userName, String note){
        //查询用户信息列表
        List<User> userList = userService.findUsers(userName, note);

        //定义PDF视图
        View view = new PdfView(exportService());
        ModelAndView mv = new ModelAndView();
        mv.setView(view);
        mv.addObject("userList", userList);
        return mv;
    }

    @SuppressWarnings("unchecked")
    private PdfExportService exportService() {
        //Lambda
        return (model, document, writer,request, response) -> {
            try{
                //A4纸张
                document.setPageSize(PageSize.A4);

                document.addTitle("用户信息");

                //换行
                document.add(new Chunk("\n"));
                //表格
                PdfPTable table = new PdfPTable(3);
                //单元格
                PdfPCell cell = null;
                //字体
                Font f8 = new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);
                cell = new PdfPCell(new Paragraph("id", f8));

                //居中对齐
                cell.setHorizontalAlignment(1);
                //将单元格加入表格
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("user_name", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);

                //获取数据模型中的用户列表
                List<User> userList = (List<User>) model.get("userList");
                for(User user : userList){
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(user.getId() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(user.getUserName()));
                    table.addCell(cell);
                    String note = user.getNote() == null ? "" : user.getNote();
                    cell = new PdfPCell(new Paragraph(note));
                    table.addCell(cell);
                }
                //在文档中加入表格
                document.add(table);

            } catch (DocumentException e){
                e.printStackTrace();;
            }
        };
    }
}
