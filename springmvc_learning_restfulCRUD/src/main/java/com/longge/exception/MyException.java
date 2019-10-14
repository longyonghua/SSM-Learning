package com.longge.exception;

/**
 * @author longge
 * @create 2019-10-14 上午11:19
 */
public class MyException extends Exception {

    private String message;

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
