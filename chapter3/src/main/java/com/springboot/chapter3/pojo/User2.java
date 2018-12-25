package com.springboot.chapter3.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/21
 **/
@Component("user2")//标明这个类被扫描进入IoC容器中
public class User2 {
    @Value("2")
    private Long id;

    @Value("user_name_2")
    private String userName;

    @Value("note_2")
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
