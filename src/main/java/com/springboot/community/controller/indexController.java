package com.springboot.community.controller;

import com.springboot.community.dto.PaginationDTO;
import com.springboot.community.mapper.UserMapper;
import com.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
    private QuestionService questionService;

    /*主页*/
    @GetMapping("/")
    public String index(Model model,
                        //分页 第1页为主  默认5个数据
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        //分页
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}

