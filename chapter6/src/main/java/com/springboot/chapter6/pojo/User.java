package com.springboot.chapter6.pojo;

import org.apache.ibatis.type.Alias;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/30
 **/
@Alias("user")
public class User {
    private Long id;
    private String userName;
    private String note;
    /**** setter and getter ****/
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
