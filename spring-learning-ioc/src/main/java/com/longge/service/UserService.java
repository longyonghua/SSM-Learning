package com.longge.service;

import com.longge.domain.User;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-07 下午2:18
 */
public interface UserService {

    List<User> findAllUser();

    User findUserById(Integer id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

}
