package com.longge.test;

import com.longge.dao.UserDao;
import com.longge.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author longge
 * @create 2019-09-30 下午4:56
 */
public class UserDaoTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before
    public void setUp() throws Exception{
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void close() throws Exception{
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testFindAll() throws Exception{
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("大大");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("星球");
        System.out.println(user.getId());
        userDao.saveUser(user);
        sqlSession.commit();
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(4);
        user.setUsername("大大1");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("星球1");
        userDao.updateUser(user);
        sqlSession.commit();
    }

    @Test
    public void testDeleteUser(){
        userDao.deleteUser(4);
        sqlSession.commit();
    }

    @Test
    public void testFindById(){
        User user = userDao.findById(2);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        //List<User> users = userDao.findByName("%longge%");
        List<User> users = userDao.findByName("longge");
        users.stream().forEach(System.out::println);
    }

    @Test
    public void testFindTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }
}
