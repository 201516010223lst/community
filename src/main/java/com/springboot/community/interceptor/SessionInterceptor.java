package com.springboot.community.interceptor;

import com.springboot.community.mapper.UserMapper;
import com.springboot.community.model.User;
import com.springboot.community.model.UserExample;
import com.springboot.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @Classname SessionInterceptor
 * @Description TODO
 * @Date 2019/10/30 18:48
 * @Created by 猪刚鬣·李
 */

@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationService notificationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取cookie，循环cookie是否找到自定义的token
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    //拿到这个cookie
                    String token = cookie.getValue();
//                System.out.println(token);
                    //在数据库里面查是不是有这个记录
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                    List<User> users = userMapper.selectByExample(userExample);
                    /*User user = userMapper.findByToken(token);*/
                    if (users.size() != 0) {
                        //user不为空，把user放到session中，在前端页面是否展示我还是登陆
//                    System.out.println("查询的token: "+user.getToken());
//                    System.out.println(user);
                        request.getSession().setAttribute("user", users.get(0));
                        Long unreadCount = notificationService.unreadCount(users.get(0).getId());
                        request.getSession().setAttribute("unreadCount",unreadCount);
                    }
                    break;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
