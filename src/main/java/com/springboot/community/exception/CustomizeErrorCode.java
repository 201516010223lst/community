package com.springboot.community.exception;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname CustomizeErrorCode
 * @Description TODO
 * @Date 2019/11/14 17:06
 * @Created by 猪刚鬣·李
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在，要不然换个问题试试？");
    @Autowired
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
