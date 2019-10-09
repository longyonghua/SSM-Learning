package com.longge.test;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author longge
 * @create 2019-10-09 下午4:25
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        //1.准备数据源（可以用dbcp、c3p0，此处使用spring的内置数据源）
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_learning?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("lyh+119988");
        //2.创建JdbcTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        //3.执行操作
        jdbcTemplate.execute("insert into account(name,money) values('aaa',1000.00)");
    }

}
