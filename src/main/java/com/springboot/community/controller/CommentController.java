package com.springboot.community.controller;

import com.springboot.community.dto.CommentDTO;
import com.springboot.community.dto.ResultDTO;
import com.springboot.community.exception.CustomizeErrorCode;
import com.springboot.community.mapper.CommentMapper;
import com.springboot.community.model.Comment;
import com.springboot.community.model.User;
import com.springboot.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Classname CommentController
 * @Description TODO
 * @Date 2019/11/19 16:58
 * @Created by 猪刚鬣·李
 */
@Controller
/*回复*/
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        /*HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("message", "成功");
        return objectObjectHashMap;*/
        return ResultDTO.okOf();
    }
}
