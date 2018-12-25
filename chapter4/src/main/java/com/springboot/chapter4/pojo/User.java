package com.springboot.chapter4.pojo;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/25
 **/
public class User {
    private Long id;

    private String username;

    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
