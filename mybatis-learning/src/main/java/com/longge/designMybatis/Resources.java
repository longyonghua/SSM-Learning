package com.longge.designMybatis;

import java.io.InputStream;

/**
 * @author longge
 * @create 2019-09-30 下午9:03
 * 使用类加载器读取配置文件的类
 */
public class Resources {

    //根据传入的参数，获取一个字节输入流
    public static InputStream getResourceAsStream(String filePath){
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }

}
