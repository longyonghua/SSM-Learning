package com.longge.designMybatis;

/**
 * @author longge
 * @create 2019-10-01 上午9:41
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private Configuration cfg;
    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
