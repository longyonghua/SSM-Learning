package com.longge.service.impl;

import com.longge.dao.AccountDao;
import com.longge.domain.Account;
import com.longge.service.AccountService;
import com.longge.util.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-08 下午7:48
 */
//事务的控制都要放在业务层
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransactionManager txManager;

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public Account findAccountByName(String name) {
        return accountDao.findAccountByName(name);
    }

    @Override
    public void saveAccount(Account account) {
        try {
            txManager.beginTransaction();
            accountDao.saveAccount(account);
            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
        }finally {
            txManager.release();
        }
    }

    @Override
    public void upadateAccount(Account account) {
        try {
            txManager.beginTransaction();
            accountDao.updateAccount(account);
            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
        }finally {
            txManager.release();
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        try {
            txManager.beginTransaction();
            accountDao.deleteAccount(id);
            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
        }finally {
            txManager.release();
        }
    }

    @Override
    public void transfer(String sourceName, String targetName, Double money) {
        try {
            txManager.beginTransaction();
            Account source = accountDao.findAccountByName(sourceName);
            Account target = accountDao.findAccountByName(targetName);
            source.setMoney(source.getMoney()-money);
            target.setMoney(target.getMoney()+money);
            accountDao.updateAccount(source);
            int i=1/0;
            accountDao.updateAccount(target);
            txManager.commit();
        }catch (Exception e){
            txManager.rollback();
        }finally {
            txManager.release();
        }
    }
}
