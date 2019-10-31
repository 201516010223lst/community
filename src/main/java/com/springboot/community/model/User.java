package com.springboot.community.model;

import lombok.Data;

/**
 * @Classname User
 * @Description TODO
 * @Date 2019/10/16 16:40
 * @Created by 猪刚鬣·李
 */
@Data
public class User {
    private Integer id;//自增
    private String accountId;//用户id
    private String name;//用户名
    private String token;//获取的token
    private Long gmtCreate;//创建时间
    private Long gmtModified;//修改时间
    private String avatarUrl;//用户头像
}
