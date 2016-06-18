package org.demo.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class RestResponse<T> {
    private Status status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;

    public RestResponse(Status status, T data) {
        this.data = data;
        this.status = status;
    }

    public RestResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
