package com.springboot.chapter16.pojo;

import org.apache.ibatis.type.Alias;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/13
 **/
@Alias("user")
public class User {

    private Long id;
    private String userName;
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
