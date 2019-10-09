package com.longge.util;

import org.aspectj.lang.JoinPoint;

/**
 * @author longge
 * @create 2019-10-09 下午1:18
 */
public class Logger {

    public void printLog(JoinPoint joinPoint){
        System.out.println("printLog执行，开始记录日志。。目标方法名："+joinPoint.getSignature().getName());
    }

}
