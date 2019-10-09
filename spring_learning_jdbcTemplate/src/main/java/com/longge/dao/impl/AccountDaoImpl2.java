package com.longge.dao.impl;

import com.longge.dao.AccountDao;
import com.longge.domain.Account;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author longge
 * @create 2019-10-09 下午5:33
 */
public class AccountDaoImpl2 extends JdbcDaoSupport implements AccountDao {
    @Override
    public Account findAccountById(Integer id) {
        return null;
    }

    @Override
    public Account findAccountByName(String name) {
        return null;
    }

    @Override
    public void updateAccount(Account account) {

    }
}
