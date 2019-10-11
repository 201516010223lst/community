package com.springboot.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Classname helloController
 * @Description TODO
 * @Date 2019/10/11 11:10
 * @Created by 猪刚鬣·李
 */
@Controller
public class indexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
