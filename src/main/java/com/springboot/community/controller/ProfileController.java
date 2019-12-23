package com.springboot.community.controller;

import com.springboot.community.dto.PaginationDTO;
import com.springboot.community.mapper.UserMapper;
import com.springboot.community.model.User;
import com.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ProfileController
 * @Description TODO
 * @Date 2019/11/5 10:23
 * @Created by 猪刚鬣·李
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    /*我的问题*/
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          //分页 第1页为主  默认5个数据
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          HttpServletRequest request,
                          Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
//            PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
//       通过用户id查找把用户提的问题发送到前端页面
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
