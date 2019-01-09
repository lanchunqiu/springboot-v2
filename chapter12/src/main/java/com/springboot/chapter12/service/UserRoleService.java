package com.springboot.chapter12.service;

import com.springboot.chapter12.pojo.DatabaseRole;
import com.springboot.chapter12.pojo.DatabaseUser;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/9
 **/
public interface UserRoleService {
    public DatabaseUser getUserByName(String userName);

    public List<DatabaseRole> findRolesByUserName(String userName);
}
