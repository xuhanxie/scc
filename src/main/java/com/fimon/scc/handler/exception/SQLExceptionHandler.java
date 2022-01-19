package com.fimon.scc.handler.exception;

import com.fimon.scc.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
public class SQLExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public Response sqlExceptionHandler(SQLException e) {
        log.error(e.toString());
        return Response.error("internal error: sql error");
    }

}
