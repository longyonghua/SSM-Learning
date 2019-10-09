package com.longge.dao;

import com.longge.domain.User;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-05 下午2:14
 */
public interface UserDao {
    List<User> findAll();
}
