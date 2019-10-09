package com.longge.dao.impl;

import com.longge.dao.AccountDao;
import com.longge.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-09 下午8:55
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
    @Override
    public Account findAccountById(Integer id) {
        String sql = "select * from account where id=?";
        List<Account> accounts = this.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<>(Account.class),id);
        return accounts.isEmpty()?null:accounts.get(0);
    }

    @Override
    public Account findAccountByName(String name) {
        String sql = "select * from account where name=?";
        List<Account> accounts = this.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<>(Account.class),name);
        if(accounts.isEmpty()){
            return null;
        }
        if(accounts.size()>1){
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        String sql = "update account set name=?,money=? where id=?";
        this.getJdbcTemplate().update(sql,account.getName(),account.getMoney(),account.getId());
    }
}
