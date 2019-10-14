package com.longge.service.impl;

import com.longge.dao.AccountDao;
import com.longge.domain.Account;
import com.longge.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-14 下午2:41
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao dao;

    @Override
    public List<Account> findAll() {
        return dao.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        dao.saveAccount(account);
    }
}
