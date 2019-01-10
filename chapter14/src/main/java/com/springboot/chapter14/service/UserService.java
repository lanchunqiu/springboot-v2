package com.springboot.chapter14.service;

import com.springboot.chapter14.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author lancq
 * @Description
 * @Date 2019/1/11
 **/
public interface UserService {
    Mono<User> getUser(Long id);

    Mono<User> insertUser(User user);

    Mono<User> updateUser(User user);

    Mono<Void> deleteUser(Long id);

    Flux<User> findUsers(String userName, String note);
}
