package com.springboot.chapter5.pojo;

import com.springboot.chapter5.enumeration.SexEnum;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/27
 **/
public class User {

    private Long id;

    private String userName;

    private SexEnum sex;

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

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
