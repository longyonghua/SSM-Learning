package com.longge.service;

import com.longge.domain.Account;

/**
 * @author longge
 * @create 2019-10-09 下午8:51
 */
public interface AccountService {

    Account findAccountById(Integer id);

    void transfer(String sourceName,String targetName,Double money);

}
