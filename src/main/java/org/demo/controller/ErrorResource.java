package org.demo.controller;

import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String code;

    private String message;

    public ErrorResource() {
    }

    public ErrorResource(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
