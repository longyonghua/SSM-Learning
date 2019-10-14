package com.longge.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author longge
 * @create 2019-10-14 上午11:27
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //获取到异常对象
        MyException me = null;
        if(e instanceof MyException){
            me = (MyException)e;
        }else{
            me = new MyException("系统正在维护..");
        }
        //创建ModelAndView
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg",me.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
