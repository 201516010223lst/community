package com.springboot.community.controller;

import com.springboot.community.mapper.UserMapper;
import com.springboot.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname helloController
 * @Description TODO
 * @Date 2019/10/11 11:10
 * @Created by 猪刚鬣·李
 */
@Controller
public class indexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String index(HttpServletRequest request){
        //获取cookie，循环cookie是否找到自定义的token
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                //拿到这个cookie
                String token=cookie.getValue();
//                System.out.println(token);
                //在数据库里面查是不是有这个记录
                User user=userMapper.findByToken(token);
                if (user!=null) {
                    //user不为空，把user放到session中，在前端页面是否展示我还是登陆
//                    System.out.println("查询的token: "+user.getToken());
//                    System.out.println(user);
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }
}
