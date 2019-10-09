package com.longge.dao.impl;

import com.longge.dao.UserDao;
import com.longge.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-07 下午2:17
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private QueryRunner runner;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public List<User> findAllUser(){
        try {
            return runner.query("select * from user", new BeanListHandler<User>(User.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public User findUserById(Integer id){
        try {
            return runner.query("select * from user where id=?", new BeanHandler<User>(User.class), id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void saveUser(User user){
        try {
            runner.update("insert into user(username,password) values(?,?)",user.getUsername(),user.getPassword());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user){
        try {
            runner.update("update user set username=?,password=? where id=?", user.getUsername(), user.getPassword(), user.getId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Integer id){
        try {
            runner.update("delete from user where id=?", id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}