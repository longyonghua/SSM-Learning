package com.longge.test;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author longge
 * @create 2019-10-07 上午11:03
 */
public class PropertiesTest {
    @Test
    public void test1() throws Exception{
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

    @Test
    public void test2() throws Exception{
        Properties props = new Properties();
        InputStream in = PropertiesTest.class.getClassLoader().getResourceAsStream("test.properties");
        props.load(in);
        String name = props.getProperty("name");
        System.out.println(name);
    }

    @Test
    public void test3() throws Exception{
        Properties props = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream("test.properties");
        props.load(in);
        String name = props.getProperty("name");
        System.out.println(name);
    }

    @Test
    public void test4() throws Exception{
        //ResourceBundle rb = ResourceBundle.getBundle("test",Locale.getDefault());
        ResourceBundle rb = ResourceBundle.getBundle("test",new Locale("en","US"));
        String name = rb.getString("name");
        System.out.println(name);
    }

}
