package com.springboot.chapter4.jdbc;

import com.springboot.chapter4.pojo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/26
 **/
public class UserService {

    public int inserUser(){
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("user_name_1");
        user.setNote("note_1");
        Connection conn = null;
        int result = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chapter3", "root", "gwssi");
            //关闭自动提交事务
            conn.setAutoCommit(false);
            result = userDao.insertUser(conn, user);
            //提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //事务回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
