package com.springboot.community.dto;

import lombok.Data;

/**
 * @Classname GithubUser
 * @Description TODO
 * @Date 2019/10/12 14:39
 * @Created by 猪刚鬣·李
 */
@Data
public class GithubUser {
    /*GitHub登录用户信息*/
    private String name;//github用户名
    private Long id;//用户id
    private String bio;//用户描述
    private String avatarUrl;//用户头像
}
