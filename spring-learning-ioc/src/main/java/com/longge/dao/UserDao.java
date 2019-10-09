package com.longge.dao;

import com.longge.domain.User;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-07 下午2:17
 */
public interface UserDao {

    List<User> findAllUser();

    User findUserById(Integer id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);
}

