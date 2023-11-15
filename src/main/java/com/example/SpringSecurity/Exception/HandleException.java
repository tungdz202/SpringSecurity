/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.Exception;

import com.example.SpringSecurity.DTO.GeneralResponse;
import java.sql.SQLException;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author ADMIN
 */
@RestControllerAdvice
@Slf4j
public class HandleException {
    @ExceptionHandler({NullPointerException.class})
    public GeneralResponse<?> handleNullPointerException(NullPointerException nullPointerException) {
        log.error("NullPointerException => rootCause: {}", Arrays.stream(nullPointerException.getStackTrace()).findFirst());
        log.error("NullPointerException => message: {}", nullPointerException.getMessage());
        return GeneralResponse.error(HttpStatusConstant.NULL_POINTER_OR_BAD_REQUEST_CODE, HttpStatusConstant.NULL_POINTER_OR_BAD_REQUEST_MESSAGE);
    }

    @ExceptionHandler({SQLException.class})
    public GeneralResponse<?> handleSQLException(SQLException ex) {
        log.error("SQLException => rootCause: {}", Arrays.stream(ex.getStackTrace()).findFirst());
        log.error("SQLException => message: {}", ex.getMessage());
        return GeneralResponse.error(HttpStatusConstant.SQL_CONNECTION_ERROR_CODE, HttpStatusConstant.SQL_CONNECTION_ERROR_MESSAGE);
    }

    @ExceptionHandler(BusinessException.class)
    public GeneralResponse<?> handleBusinessException(BusinessException ex) {
        return GeneralResponse.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public GeneralResponse<?> handleException(Exception ex) {
        log.error("Exception => rootCause: {}", Arrays.stream(ex.getStackTrace()).findFirst());
        log.error("Exception => message: {}", ex.getMessage());
        return GeneralResponse.error(HttpStatusConstant.UNAVAILABLE_CODE, HttpStatusConstant.UNAVAILABLE_MESSAGE);
    }
}
