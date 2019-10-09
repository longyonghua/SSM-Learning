package com.longge.designMybatis;


/**
 * @author longge
 * @create 2019-09-30 下午9:13
 */
public interface SqlSessionFactory {
    //用于打开一个新的SqlSession对象
    SqlSession openSession();

}
