package com.springboot.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/**
 * @Classname WebConfig
 * @Description TODO
 * @Date 2019/10/30 18:48
 * @Created by 猪刚鬣·李
 */
@Configuration
/*@EnableWebMvc*/

public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //在所有路径下拦截
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");

    }
}

