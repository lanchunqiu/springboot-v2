package com.springboot.chapter12.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/9
 **/
@Alias("dbrole")
public class DatabaseRole implements Serializable {
    private static final long serialVersionUID = -9187344495447048574L;

    private Long id;
    private String roleName;
    private String note;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "DatabaseRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
