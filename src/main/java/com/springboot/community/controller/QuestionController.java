package com.springboot.community.controller;

import com.springboot.community.dto.CommentDTO;
import com.springboot.community.dto.QuestionDTO;
import com.springboot.community.enums.CommentTypeEnum;
import com.springboot.community.service.CommentService;
import com.springboot.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Classname QuestionController
 * @Description TODO
 * @Date 2019/11/7 16:54
 * @Created by 猪刚鬣·李
 */
@Controller
/*QuestionController*/
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    /*问题编辑*/
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        List<QuestionDTO> relatedQuestions=questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //通过id来累加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments",comments);
        //相关问题
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
