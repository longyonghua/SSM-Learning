package com.longge.controller;

import com.longge.entities.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author longge
 * @create 2019-10-10 下午8:52
 */
@Controller
@RequestMapping("springmvc")
public class Helloworld {

    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.GET)
    public String testRest1(@PathVariable Integer id){
        System.out.println("testRest get:"+id);
        return "success";
    }

    @RequestMapping(value = "/testRest",method = RequestMethod.POST)
    public String testRest2(){
        System.out.println("testRest post");
        return "success";
    }

    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.PUT)
    public String testRest3(@PathVariable Integer id){
        System.out.println("testRest put:"+id);
        return "success";
    }

    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.DELETE)
    public String testRest4(@PathVariable Integer id){
        System.out.println("testRest delete:"+id);
        return "success";
    }

    @RequestMapping("/testConverter")
    public String testConverter(User user){
        System.out.println(user);
        return "success";
    }

    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(User user){
        System.out.println(user); //User{name='longge', age=18, birthday=Fri Oct 11 17:05:21 CST 2019}
        return "success";
    }
    @ModelAttribute
    public User showUser(String name,Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setBirthday(new Date());
        return user;
    }

    @RequestMapping("/testString")
    public String testString(Model model){
        User user = new User("longge",18,new Date());
        model.addAttribute("user",user);
        return "success";
    }

    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("testVoid执行了");
        //请求转发
        //request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request,response);
        //重定向  注意：重定向不能访问/WEB-INF目录下的资源
        //response.sendRedirect(request.getContextPath()+"/hello.jsp");
        //直接进行响应
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("longge大佬");
        return;
    }

    @RequestMapping("testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mav = new ModelAndView();
        User user = new User("longge",28,new Date());
        mav.setViewName("success");
        mav.addObject("user",user);
        return mav;
    }

    @RequestMapping("/testAjax")
    @ResponseBody
    public User testAjax(@RequestBody User user){
        System.out.println(user);
        user.setName("老头");
        user.setAge(49);
        user.setBirthday(new Date());
        return user;
    }

    @RequestMapping("/testUpload")
    public String testUpload(HttpServletRequest request) throws Exception{
        //设置上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        //解析request，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);

        //遍历
        for(FileItem item : items){
            //判断当前item是上传文件项，还是普通表单项
            if(item.isFormField()){ //是普通表单项，直接获取数据
                String inputName = item.getFieldName();
                String inputValue = item.getString();
                System.out.println(inputName+"="+inputValue);
            }else{ //是上传文件项
                String filename = item.getName(); //上传的文件名称
                String uuid = UUID.randomUUID().toString().replace("-","");
                filename = uuid + "_" +filename; //使用UUID构造一个唯一的文件名
                item.write(new File(path,filename)); //完成文件上传
                item.delete(); //删除临时文件
            }
        }

        return "success";
    }

    @RequestMapping("/testUpload2")
    public String testUpload2(HttpServletRequest request, MultipartFile upload) throws Exception{
        //设置上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //获得文件名称
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid + "_" +filename; //使用UUID构造一个唯一的文件名
        //完成文件上传
        upload.transferTo(new File(path,filename));

        return "success";
    }

    @RequestMapping("/testUpload3")
    public String testUpload3(MultipartFile upload) throws Exception{
        //定义上传文件服务器路径
        String path = "http://localhost:9090/fileuploadserver/uploads/";

        //获得文件名称
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid + "_" +filename; //使用UUID构造一个唯一的文件名

        //跨服务器上传文件
        //1.创建客户端对象
        Client client = Client.create();
        //2.与图片服务器进行连接
        WebResource webResource = client.resource(path+filename); //若path后面没有"/"，则这里需要写成：path+"/"+filename
        //3.上传文件
        webResource.put(upload.getBytes());

        return "success";
    }

}
