package com.springboot.chapter12.dao;

import com.springboot.chapter12.pojo.DatabaseRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/9
 **/
@Repository
public interface RoleDao {
    public List<DatabaseRole> findRolesByUserName(String userName);
}
