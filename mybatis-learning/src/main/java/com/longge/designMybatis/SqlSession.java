package com.longge.designMybatis;

/**
 * @author longge
 * @create 2019-09-30 下午9:14
 */
public interface SqlSession {

    //根据参数创建一个代理对象
    <T> T getMapper(Class<T> daoInterfaceClass);

    void close();

}
