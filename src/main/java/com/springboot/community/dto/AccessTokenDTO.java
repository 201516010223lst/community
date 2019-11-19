package com.springboot.community.dto;

import lombok.Data;

/**
 * @Classname AccessTokenDTO
 * @Description TODO
 * @Date 2019/10/12 11:45
 * @Created by 猪刚鬣·李
 */
@Data
/*AccessTokenDTO*/
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
