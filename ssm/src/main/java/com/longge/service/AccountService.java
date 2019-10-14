package com.longge.service;

import com.longge.domain.Account;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-14 下午2:40
 */
public interface AccountService {

    List<Account> findAll();

    void saveAccount(Account account);

}
