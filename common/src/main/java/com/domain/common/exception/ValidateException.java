package com.domain.common.exception;


/**
 * @author leone
 **/
public class ValidateException extends RuntimeException {

    private Integer code;

    private String message;

    public ValidateException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ValidateException() {
    }

    public ValidateException(ExceptionMessage messageEnum) {
        this.code = messageEnum.getCode();
        this.message = messageEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
