package com.wmp.exception;

public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 5984337968852338375L;
    private String message;

    public CommonException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
