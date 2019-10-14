package com.longge.test;

import com.longge.domain.Account;
import com.longge.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author longge
 * @create 2019-10-14 下午2:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestSpring {
    @Autowired
    private AccountService service;

    @Test
    public void teseSaveAccount(){
        service.saveAccount(new Account());
    }

}
