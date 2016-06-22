package org.demo.controller;

import org.demo.controller.response.ResponseData;
import org.demo.controller.response.Status;
import org.demo.exception.ApiException;
import org.demo.exception.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    private ResponseData getResponseDataInternal (Throwable throwable) {
        ResponseData responseData = new ResponseData(Status.Failure(throwable));
        return responseData;
    }

    @ExceptionHandler(ApiException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseData apiExceptionResolver(Throwable throwable) {
        System.out.println("****************** ExceptionController.apiExceptionResolver");

        return getResponseDataInternal(throwable);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseData hibernateExceptionResolver(Throwable throwable) {
        System.out.println("****************** ExceptionController.hibernateExceptionResolver");

        return getResponseDataInternal(throwable);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseData userNotFoundExceptionResolver(Throwable throwable) {
        System.out.println("****************** ExceptionController.userNotFoundExceptionResolver");
        return getResponseDataInternal(throwable);
    }
}
