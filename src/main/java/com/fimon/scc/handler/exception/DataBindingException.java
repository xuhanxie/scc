package com.fimon.scc.handler.exception;

import com.fimon.scc.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DataBindingException {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response typeMismatchException(HttpMessageNotReadableException e) {
        log.error(e.toString());
        return Response.error("invalid params: " + e.getMessage());
    }

}
