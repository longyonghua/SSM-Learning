package com.longge.designMybatis;


import java.io.InputStream;

/**
 * @author longge
 * @create 2019-09-30 下午9:09
 * 用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }

}
