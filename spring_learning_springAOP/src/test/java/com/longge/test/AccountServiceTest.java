package com.longge.test;

import com.longge.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author longge
 * @create 2019-10-09 下午1:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccountServiceTest {
    @Autowired
    private AccountService service;

    @Test
    public void test1(){
        service.saveAccount();
        service.updateAccount(1);
        service.deleteAccount();
    }


}
