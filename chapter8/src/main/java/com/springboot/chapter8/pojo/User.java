package com.springboot.chapter8.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/31
 **/
@Document
public class User implements Serializable {
    //MongoDB文档编号，主键
    @Id
    private Long id;

    //在MongoDB中使用user_name保存属性
    @Field("user_name")
    private String userName = null;

    private String note = null;

    //角色列表
    private List<Role> roles = null;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", note='" + note + '\'' +
                ", roles=" + roles +
                '}';
    }
}
