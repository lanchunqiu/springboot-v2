package com.springboot.chapter12.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/9
 **/
@Alias("dbuser")
public class DatabaseUser implements Serializable {
    private static final long serialVersionUID = -7999354910231863737L;

    private Long id;
    private String userName;
    private String pwd;
    private Boolean available;
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
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "DatabaseUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", available=" + available +
                ", note='" + note + '\'' +
                '}';
    }
}
