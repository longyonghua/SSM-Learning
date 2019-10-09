package com.longge.designMybatis;

import com.longge.dao.UserDao;
import com.longge.domain.User;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author longge
 * @create 2019-10-01 上午11:18
 */
public class MyTest {
    @Test
    public void testSelect(){
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao dao = sqlSession.getMapper(UserDao.class);
        List<User> users = dao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        sqlSession.close();
    }
}
