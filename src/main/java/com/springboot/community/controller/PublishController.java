package com.springboot.community.controller;

import com.springboot.community.mapper.QuestionMapper;
import com.springboot.community.mapper.UserMapper;
import com.springboot.community.model.Question;
import com.springboot.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname PublishController
 * @Description TODO
 * @Date 2019/10/28 12:01
 * @Created by 猪刚鬣·李
 */
/*发布功能控制层*/
@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    /*@GetMapping用于将Http Get 请求映射到特定处理程序方法的注释*/
    /*一般情况下@RequestMapping都可以代替@GetMapping和@PostMapping两个注释*/
    public String publish() {
        return "publish";
    }

    /*接收前端页面传过来的参数，请求方式为post*/
    @PostMapping("/publish")
    /*@PostMapping用于将Http Post 请求映射到特定处理程序方法的注释。是@RequestMapping(method = RequestMethod.POST)的缩写。
    method=RequestMethod.POST：只接受post请求，其他的不行，如果接收的不是post请求会405报错*/
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model
    ) {
        /*把数据放到model，回显到前端页面*/
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        /*判断输入的内容不能为空*/

        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        /*判断用户是否登录*/
      /*  User user = null;
        //获取cookie，循环cookie是否找到自定义的token
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {//先判断cookies是否为空，否则会报空指针错误
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    //拿到这个cookie
                    String token = cookie.getValue();
//                System.out.println(token);
                    //在数据库里面查是不是有这个记录
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        //user不为空，把user放到session中，在前端页面是否展示我还是登陆
//                    System.out.println("查询的token: "+user.getToken());
//                    System.out.println(user);
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }*/
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        /*把问题等信息写到数据库中*/
        questionMapper.create(question);
        return "redirect:/";
    }
}
