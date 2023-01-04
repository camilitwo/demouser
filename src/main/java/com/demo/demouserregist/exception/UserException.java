package com.demo.demouserregist.exception;

public class UserException  extends Exception {

    private Integer code;
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
