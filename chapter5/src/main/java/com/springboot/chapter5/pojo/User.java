package com.springboot.chapter5.pojo;

import com.springboot.chapter5.converter.SexConverter;
import com.springboot.chapter5.enumeration.SexEnum;

import javax.persistence.*;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/27
 **/
//标明是一个实体类
@Entity(name = "user")
//定义映射的表
@Table(name = "t_user")
public class User {
    //标明主键
    @Id
    //主键策略，递增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //定义属性和表的映射关系
    @Column(name = "user_name")
    private String userName;

    //定义装换器
    @Convert(converter = SexConverter.class)
    private SexEnum sex;

    @Column(name ="note")
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
