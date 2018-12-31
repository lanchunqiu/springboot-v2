package com.springboot.chapter8.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.springboot.chapter8.pojo.User;
import com.springboot.chapter8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lancq
 * @Description
 * @Date 2018/12/31
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate = null;

    @Override
    public void saveUser(User user) {
        //使用用户名为user文档保存用户信息
        mongoTemplate.save(user, "user");

        //如果文档采用首字母小写，则可以这样保存
        //mongoTemplate.save(user);
    }

    @Override
    public DeleteResult deleteUser(Long id) {
        Criteria criteria = Criteria.where("id").is(id);
        Query query = Query.query(criteria);
        DeleteResult result = mongoTemplate.remove(query, User.class);
        return result;
    }

    @Override
    public List<User> findUser(String userName, String note, int skip, int limit) {

        //将用户名和备注设置为模糊查询准则
        Criteria criteria = Criteria.where("userName").regex(userName).and("note").regex(note);
        //构建查询条件，并设置分页跳过前skip个，最多返回limit个
        Query query = Query.query(criteria).limit(limit).skip(skip);
        List<User> userList = mongoTemplate.find(query, User.class);
        return userList;

    }

    @Override
    public UpdateResult updateUser(Long id, String userName, String note) {
        Criteria criteria = Criteria.where("id").is(id);
        Query query = Query.query(criteria);
        //定义更新对象
        Update update = Update.update("userName", userName);
        update.set("note",note);
        //更新第一个文档
        UpdateResult result = mongoTemplate.updateFirst(query,update,User.class);
        //更新更多对象
        //UpdateResult result = mongoTemplate.updateMulti(query,update,User.class);
        return result;
    }

    @Override
    public User getUser(Long id) {
        return mongoTemplate.findById(id, User.class);

        /*//如果只获取第一个，也可以采用如下查询方法
        Criteria criteria = Criteria.where("id").is(id);
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query,User.class);*/
    }
}
