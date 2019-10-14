package com.longge.dao;

import com.longge.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longge
 * @create 2019-10-14 下午2:39
 */
@Repository
public interface AccountDao {

    @Select("select * from account")
    List<Account> findAll();

    @Insert("insert into account(name,money) values(#{name},#{money})")
    void saveAccount(Account account);

}
