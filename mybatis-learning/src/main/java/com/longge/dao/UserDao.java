package com.longge.dao;

import com.longge.designMybatis.Select;
import com.longge.domain.User;

import java.util.List;

/**
 * @author longge
 * @create 2019-09-30 下午4:08
 */
public interface UserDao {
    //@Select("select * from user")
    List<User> findAll();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    User findById(Integer id);

    List<User> findByName(String name); //根据名称模糊查询

    int findTotal();
}
