package org.demo.controller;

import org.demo.controller.response.RestResponseData;
import org.demo.controller.response.Status;
import org.demo.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    private RestResponseData getResponseDataInternal (Throwable throwable) {
        RestResponseData responseData = new RestResponseData(Status.Failure(throwable));
        return responseData;
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestResponseData apiExceptionResolver(Throwable throwable) {
        System.out.println("****************** ExceptionController.apiExceptionResolver");
        return getResponseDataInternal(throwable);

    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    @org.springframework.web.bind.annotation.ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public RestResponseData hibernateExceptionResolver(Throwable throwable) {
//        System.out.println("****************** ExceptionController.hibernateExceptionResolver");
//
//        RestResponseData responseData = new RestResponseData(Status.Failure(throwable));
//        return responseData;
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public RestResponseData userNotFoundExceptionResolver(Throwable throwable) {
//        System.out.println("****************** ExceptionController.userNotFoundExceptionResolver");
//        RestResponseData responseData = new RestResponseData(Status.Failure(throwable));
//        return responseData;
//    }
}
