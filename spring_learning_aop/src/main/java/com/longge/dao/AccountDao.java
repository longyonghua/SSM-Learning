package com.longge.dao;

import com.longge.domain.Account;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-08 下午8:06
 */
public interface AccountDao {
    List<Account> findAllAccount();

    Account findAccountById(Integer id);

    Account findAccountByName(String name);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer id);
}
