package com.springboot.community.controller;

import com.springboot.community.dto.PaginationDTO;
import com.springboot.community.dto.QuestionDTO;
import com.springboot.community.mapper.QuestionMapper;
import com.springboot.community.mapper.UserMapper;
import com.springboot.community.model.Question;
import com.springboot.community.model.User;
import com.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname helloController
 * @Description TODO
 * @Date 2019/10/11 11:10
 * @Created by 猪刚鬣·李
 */
//主页控制层
@Controller
public class indexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        //分页 第1页为主  默认5页
                        @RequestParam(name = "page",defaultValue ="1") Integer page,
                        @RequestParam(name = "size",defaultValue ="5") Integer size
                        ) {
        //获取cookie，循环cookie是否找到自定义的token
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    //拿到这个cookie
                    String token = cookie.getValue();
//                System.out.println(token);
                    //在数据库里面查是不是有这个记录
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        //user不为空，把user放到session中，在前端页面是否展示我还是登陆
//                    System.out.println("查询的token: "+user.getToken());
//                    System.out.println(user);
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
//        分页
        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}

