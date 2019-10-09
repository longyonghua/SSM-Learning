package com.longge.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author longge
 * @create 2019-10-07 上午11:03
 */
public class PropertiesTest {
    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        String path = PropertiesTest.class.getClassLoader().getResource("test.properties").getPath();
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        props.load(in);
        Enumeration enums = props.propertyNames();
        while(enums.hasMoreElements()){
            String key = (String)enums.nextElement();
            String value = props.getProperty(key);
            System.out.println(key+"="+value);
        }
    }

}
