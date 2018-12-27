package com.springboot.chapter5.service.impl;

import com.springboot.chapter5.enumeration.SexEnum;
import com.springboot.chapter5.pojo.User;
import com.springboot.chapter5.service.JdbcTmplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/27
 **/
@Service
public class JdbcTmplUserServiceImpl implements JdbcTmplUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate = null;

    /**
     * 获取映射关系
     * @return
     */
    private RowMapper<User> getUserMapper(){
        RowMapper<User> userRowMapper = (ResultSet rs, int rownum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            int sexId = rs.getInt("sex");
            SexEnum sex = SexEnum.getEnumById(sexId);
            user.setSex(sex);
            user.setNote(rs.getString("note"));
            return user;
        };
        return userRowMapper;
    }

    @Override
    public User getUser(Long id) {
        String sql = "select id, user_name, sex, note from t_user where id = ?";
        Object[] params = new Object[]{id};
        User user = jdbcTemplate.queryForObject(sql, params, getUserMapper());
        return user;
    }

    public User getUser2(Long id) {
        //通过Lambda表达式使用StatementCallback
        User result = this.jdbcTemplate.execute((Statement stmt)->{
            String sql1 = "select count(*) as total from t_user where id = ?";
            String sql2 = "select id, user_name, sex, note from t_user where id = ?";

            ResultSet rs1 = stmt.executeQuery(sql1);
            while(rs1.next()){
                int total = rs1.getInt("total");
                System.out.println(total);
            }

            ResultSet rs2 = stmt.executeQuery(sql2);
            User user = null;
            while(rs2.next()){
                int rowNum = rs2.getRow();
                user = getUserMapper().mapRow(rs2, rowNum);
            }
            return user;
        });

        return result;
    }

    public User getUser3(Long id) {
        return null;
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        String sql = "select id, user_name, sex, note from t_user "
                + "where user_name like concat('%', ?, '%') and note like concat('%', ?, '%')";
        Object[] params = new Object[]{userName, note};
        List<User> userList = jdbcTemplate.query(sql, params, getUserMapper());
        return userList;

    }

    @Override
    public int insertUser(User user) {
        String sql = "insert into t_user(user_name, sex, note) values(?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getSex().getId(), user.getNote());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user set user_name = ?, sex = ?, note = ? where id = ?";
        return jdbcTemplate.update(sql, user.getUserName(), user.getSex().getId(), user.getNote(), user.getId());
    }

    @Override
    public int deleteUser(Long id) {
        String sql = "delete from t_user where id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
