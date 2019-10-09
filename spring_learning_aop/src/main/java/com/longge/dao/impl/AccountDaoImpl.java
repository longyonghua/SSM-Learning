package com.longge.dao.impl;

import com.longge.dao.AccountDao;
import com.longge.domain.Account;
import com.longge.util.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.List;

/**
 * @author longge
 * @create 2019-10-08 下午7:50
 */
@Repository
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private QueryRunner runner;
    @Autowired
    private ConnectionUtil connectionUtil;

    @Override
    public List<Account> findAllAccount() {
        try{
            return runner.query(connectionUtil.getThreadConnection(),"select * from account",new BeanListHandler<Account>(Account.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountById(Integer id) {
        try{
            return runner.query(connectionUtil.getThreadConnection(),"select * from account where id=?",new BeanHandler<Account>(Account.class),id);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByName(String name) {
        try{
            List<Account> accounts = runner.query(connectionUtil.getThreadConnection(),"select * from account where name=?",new BeanListHandler<Account>(Account.class),name);
            if(accounts==null || accounts.size()==0){
                return null;
            }
            if(accounts.size()>1){
                throw new RuntimeException("结果集不唯一，数据有问题");
            }
            return accounts.get(0);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try{
            runner.update(connectionUtil.getThreadConnection(),"insert into account(name,money) values(?,?)",account.getName(),account.getMoney());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            runner.update(connectionUtil.getThreadConnection(),"update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        try{
            runner.update(connectionUtil.getThreadConnection(),"delete * from account where id=?",id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
