package com.springboot.community.dto;

import lombok.Data;

/**
 * @Classname CommentDTO
 * @Description TODO
 * @Date 2019/11/19 17:07
 * @Created by 猪刚鬣·李
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
