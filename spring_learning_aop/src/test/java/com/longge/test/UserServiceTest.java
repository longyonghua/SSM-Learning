package com.longge.test;

import com.longge.domain.Account;
import com.longge.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author longge
 * @create 2019-10-08 下午7:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {
    @Autowired
    private AccountService service;

    @Test
    public void testSaveAccount(){
        Account account = new Account();
        account.setName("longge2");
        account.setMoney(800.00);
        service.saveAccount(account);
    }

    @Test
    public void testTransfer(){
        service.transfer("longge1","longge2",100.00);
    }
}
