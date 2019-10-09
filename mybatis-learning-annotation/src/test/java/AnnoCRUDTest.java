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
 * @create 2019-10-06 上午9:02
 */
public class AnnoCRUDTest {
    InputStream in;
    SqlSessionFactory factory;
    SqlSession session;
    UserDao userDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void close() throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void findAllTest(){
        List<User> users = userDao.findAll();
        users.stream().forEach(System.out::println);
    }

    @Test
    public void saveUserTest(){
        User user = new User();
        user.setUsername("heihei");
        user.setSex("M");
        userDao.saveUser(user);
    }

    @Test
    public void updateUserTest(){
        User user = new User();
        user.setId(7);
        user.setUsername("hehe");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserTest(){
        userDao.deleteUser(7);
    }

    @Test
    public void findOneTest(){
        User user = userDao.findById(6);
        System.out.println(user);
    }

    @Test
    public void findUserByNameTest(){
        //List<User> users = userDao.findUserByName("%long%");
        List<User> users = userDao.findUserByName("long");
        users.stream().forEach(System.out::println);
    }

    @Test
    public void findCountTest(){
        int count = userDao.findCount();
        System.out.println(count);
    }
}
