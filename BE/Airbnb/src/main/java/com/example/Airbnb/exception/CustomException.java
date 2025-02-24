package com.example.Airbnb.exception;

public class CustomException extends RuntimeException {
    private String message;
    private int statusCode = 500 ;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
