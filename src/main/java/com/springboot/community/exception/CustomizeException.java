package com.springboot.community.exception;

/**
 * @Classname CustomizeException
 * @Description TODO
 * @Date 2019/11/14 17:00
 * @Created by 猪刚鬣·李
 */
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
