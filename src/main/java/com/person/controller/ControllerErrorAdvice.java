package com.person.controller;


import com.person.dto.JSONResponse;
import com.person.exception.ResourceNotFoundException;
import com.person.helper.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ControllerErrorAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<JSONResponse> handleException(ConstraintViolationException exception, HttpServletRequest request) {
        log.warn(exception.getMessage());
        return ResponseHelper.badRequest(exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<JSONResponse> handleException(ResourceNotFoundException exception, HttpServletRequest request) {
        log.warn(exception.getMessage());
        return ResponseHelper.notFound(exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<JSONResponse> handleException(DataIntegrityViolationException exception, HttpServletRequest request) {
        log.warn(exception.getMessage());
        return ResponseHelper.badRequest("Unable to perform operation due to data integrity violation.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONResponse> handleException(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();
        log.error(exception.getMessage());
        return ResponseHelper.internalError("Internal Server Error.");
    }

}
