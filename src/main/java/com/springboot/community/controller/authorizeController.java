package com.springboot.community.controller;

import com.springboot.community.dto.AccessTokenDTO;
import com.springboot.community.dto.GithubUser;
import com.springboot.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname authorizeController
 * @Description TODO
 * @Date 2019/10/12 11:33
 * @Created by 猪刚鬣·李
 */
@Controller
public class authorizeController {
    /*自动识别spring容器*/
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("Iv1.826e125ed735f67a");
        accessTokenDTO.setClient_secret("c2e0f4ed1d2bb8dd2a5ed34a9dd1315414ea020c");
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
