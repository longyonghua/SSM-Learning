package com.longge.springmvc;

import com.longge.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * @author longge
 * @create 2019-10-10 下午1:59
 */
@Controller
@RequestMapping("springmvc")
public class Helloworld2 {

    @RequestMapping("test")
    public String test(User user, HttpServletRequest request, HttpServletResponse response, Writer out) throws Exception{
        System.out.println(user);
        System.out.println(request+"---"+response);
        out.write("hello longge");
        return "success";
    }

    @RequestMapping("testview")
    public String testView(){
        System.out.println("testview");
        return "helloview";
    }

    @RequestMapping("redirect")
    public String testRedirect(){
        System.out.println("testRedirect");
        return "redirect:/redirect.jsp";
    }
}
