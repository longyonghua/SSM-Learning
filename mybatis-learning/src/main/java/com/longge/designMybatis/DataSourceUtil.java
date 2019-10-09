package com.longge.designMybatis;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author longge
 * @create 2019-10-01 上午11:13
 */
public class DataSourceUtil {
    public static Connection getConnection(Configuration cfg){
        try{
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}
