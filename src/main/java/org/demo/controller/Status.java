package org.demo.controller;

public class Status {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";

    private String result;
    private String message;

    public static final Status Success () {
        return new Status(SUCCESS, "");
    }
    public static final Status Failure (Throwable throwable) {
        return new Status(FAILURE, throwable.getMessage());
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public Status() {
    }
}
