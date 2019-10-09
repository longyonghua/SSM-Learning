package com.longge.dao;

import com.longge.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-06 上午8:10
 */
public interface UserDao {

    @Select("select * from user")
    List<User> findAll();

    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    @Update("update user set username=#{username},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void updateUser(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);

    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    @Select("select * from user where username like '%${value}%'")
    //@Select("select * from user where username like #{username}")
    List<User> findUserByName(String username);

    @Select("select count(*) from user")
    int findCount();

}
