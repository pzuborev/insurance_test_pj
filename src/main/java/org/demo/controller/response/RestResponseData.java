package org.demo.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class RestResponseData<T> {
    private Status status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;

    public RestResponseData(Status status, T data) {
        this.data = data;
        this.status = status;
    }

    public RestResponseData(Status status) {
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
