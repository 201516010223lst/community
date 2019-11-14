package com.springboot.community.exception;

/**
 * @Classname CustomizeException
 * @Description TODO
 * @Date 2019/11/14 17:00
 * @Created by 猪刚鬣·李
 */
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
