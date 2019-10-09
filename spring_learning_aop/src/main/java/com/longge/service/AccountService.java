package com.longge.service;

import com.longge.domain.Account;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-08 下午7:43
 */
public interface AccountService {

    List<Account> findAllAccount();

    Account findAccountById(Integer id);

    Account findAccountByName(String name);

    void saveAccount(Account account);

    void upadateAccount(Account account);

    void deleteAccount(Integer id);

    void transfer(String sourceName,String targetName,Double money);

}
