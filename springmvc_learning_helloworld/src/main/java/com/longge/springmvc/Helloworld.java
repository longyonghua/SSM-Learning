package com.longge.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author longge
 * @create 2019-10-10 下午1:26
 */
@Controller
public class Helloworld {

    @RequestMapping("/helloworld")
    public String hello(){
        System.out.println("hello world");
        System.out.println("hello11");
        return "success";
    }

}
