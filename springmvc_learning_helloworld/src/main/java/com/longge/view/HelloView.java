package com.longge.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author longge
 * @create 2019-10-10 下午7:32
 */
@Component("helloview")
public class HelloView implements View {
    @Override
    public String getContentType() {
        return "text/html;charset=UTF-8";
        //return null;
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.addHeader("Content-Type","text/html;charset=UTF-8");
        response.getWriter().println("龙哥好");
    }
}
