package com.longge.dao;

import com.longge.domain.Account;

/**
 * @author longge
 * @create 2019-10-09 下午5:02
 */
public interface AccountDao {

    Account findAccountById(Integer id);

    Account findAccountByName(String name);

    void updateAccount(Account account);

}
