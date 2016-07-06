package org.demo.controller;

import org.demo.exception.ApiException;
import org.demo.exception.ResourceNotFoundException;
import org.demo.exception.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    private ResponseEntity<ErrorResource> getResponseDataInternal (Throwable throwable, HttpStatus statusCode) {
        return new ResponseEntity(new ErrorResource(throwable.getMessage()), statusCode);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResource> apiExceptionResolver(Throwable throwable) {
        System.out.println("****************** ExceptionController.apiExceptionResolver");
        return getResponseDataInternal(throwable, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResource> hibernateExceptionResolver(Throwable throwable) {
        System.out.println("****************** ExceptionController.hibernateExceptionResolver");
        return getResponseDataInternal(throwable, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> userNotFoundExceptionResolver(Exception ex, WebRequest request) {
//        System.out.println("****************** ExceptionController.userNotFoundExceptionResolver");
//        return getResponseDataInternal(throwable);
//        String bodyOfResponse = "This should be application specific " + ex.getMessage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, new ErrorResource(ex.getMessage()),
                headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResource> resourceNotFoundExceptionResolver(Throwable throwable) {
        System.out.println("****************** ExceptionController.resourceNotFoundExceptionResolver");
        return getResponseDataInternal(throwable, HttpStatus.BAD_REQUEST);
    }
}
