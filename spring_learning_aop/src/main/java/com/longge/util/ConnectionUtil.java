package com.longge.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author longge
 * @create 2019-10-09 上午9:11
 */
@Component
public class ConnectionUtil {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    @Autowired
    DataSource dataSource;

    //获取当前线程上的连接
    public Connection getThreadConnection(){
        try {
            Connection conn = threadLocal.get(); //先从ThreadLocal上获取连接
            if (conn == null) { //判断是否获取到了
                conn = dataSource.getConnection(); //从数据源中获取一个连接，并存入当前线程
                threadLocal.set(conn);
            }
            return conn; //返回当前线程上的连接
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //把连接和线程解绑
    public void removeConnection(){
        threadLocal.remove();
    }

}
