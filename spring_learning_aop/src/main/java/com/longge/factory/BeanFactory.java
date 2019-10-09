package com.longge.factory;

import com.longge.service.AccountService;
import com.longge.util.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author longge
 * @create 2019-10-09 上午11:22
 */
public class BeanFactory {
    @Autowired
    private AccountService service;
    @Autowired
    private TransactionManager txManager;

    public AccountService getAccountService(){
        return (AccountService) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object resultValue = null;
                        try {
                            txManager.beginTransaction();
                            resultValue = method.invoke(service,args);
                            txManager.commit();
                            return resultValue;
                        }catch (Exception e){
                            txManager.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            txManager.release();
                        }
                    }
                }
        );
    }

}
