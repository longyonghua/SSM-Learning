package com.longge.test;

import com.longge.dao.AccountDao;
import com.longge.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author longge
 * @create 2019-10-09 下午5:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JdbcTemplateDemo2 {
    @Autowired
    AccountDao dao;

    @Test
    public void testFindAccountById(){
        Account account = dao.findAccountById(5);
        System.out.println(account);
    }

}
