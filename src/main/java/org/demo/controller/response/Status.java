package org.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Status {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";

    private String result;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    public static final Status Success () {
        return new Status(SUCCESS, "");
    }
    public static final Status Failure (Throwable throwable) {
        throwable.printStackTrace();

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
