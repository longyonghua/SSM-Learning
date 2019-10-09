package com.longge.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author longge
 * @create 2019-10-09 上午9:22
 */
@Component
public class TransactionManager {
    @Autowired
    ConnectionUtil connectionUtil;

    //开启事务
    public void beginTransaction(){
        try{
            connectionUtil.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //提交事务
    public void commit(){
        try{
            connectionUtil.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //回滚事务
    public void rollback(){
        try{
            connectionUtil.getThreadConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //释放连接
    public void release(){
        try{
            connectionUtil.getThreadConnection().close(); //还回到连接池中
            connectionUtil.removeConnection(); //应该将该连接与本线程解绑，否则下次在本线程上获取到的连接是close过的
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
