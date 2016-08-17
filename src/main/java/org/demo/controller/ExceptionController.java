package org.demo.controller;

import org.apache.log4j.Logger;
import org.demo.exception.ApiException;
import org.demo.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    final static Logger logger = Logger.getLogger(ExceptionController.class);

    private ResponseEntity<ErrorResource> getResponseDataInternal(Throwable throwable, HttpStatus statusCode) {
        System.out.println(" ***** getResponseDataInternal ");
        return new ResponseEntity(new ErrorResource(throwable.getMessage()), statusCode);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResource> apiExceptionResolver(Throwable throwable) {
        logger.debug("*** apiExceptionResolver");
        return getResponseDataInternal(throwable, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResource> hibernateExceptionResolver(Throwable throwable) {
        logger.debug("*** hibernateExceptionResolver");
        return getResponseDataInternal(throwable, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> userNotFoundExceptionResolver(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, new ErrorResource(String.format("User %s not found", ex.getMessage())),
                headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResource> resourceNotFoundExceptionResolver(Throwable throwable) {
        logger.debug("*** resourceNotFoundExceptionResolver");
        return getResponseDataInternal(throwable, HttpStatus.BAD_REQUEST);
    }
}
