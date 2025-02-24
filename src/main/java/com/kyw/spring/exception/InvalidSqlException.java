package com.kyw.spring.exception;

// 自定义异常
public class InvalidSqlException extends Exception {
    public InvalidSqlException(String message) {
        super(message);
    }
}
