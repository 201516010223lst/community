package com.springboot.community.model;

import lombok.Data;

/**
 * @Classname Question
 * @Description TODO
 * @Date 2019/10/28 15:34
 * @Created by 猪刚鬣·李
 */
@Data
public class Question {
    private Integer id;//问题id
    private String title;//问题标题
    private String description;//问题描述
    private String tag;//问题标签
    private Long gmtCreate;//创建时间
    private Long gmtModified;//修改时间
    private Integer creator;//用户表主键id
    private Integer viewCount;//浏览数量
    private Integer commentCount;//阅读数量
    private Integer likeCount;//关注数量
}
