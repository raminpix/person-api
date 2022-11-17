package com.person.controller;


import com.person.dto.JSONResponse;
import com.person.exception.ResourceNotFoundException;
import com.person.helper.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<JSONResponse> handleException(AccessDeniedException exception, HttpServletRequest request) {
        log.warn(exception.getMessage());
        return ResponseHelper.forbidden(exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<JSONResponse> handleException(DataIntegrityViolationException exception, HttpServletRequest request) {
        log.warn(exception.getMessage());
        return ResponseHelper.badRequest("Unable to perform operation due to data integrity violation.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JSONResponse> handleException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        log.warn(exception.getMessage());
        return ResponseHelper.badRequest("Unable to perform operation due to invalid data.");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<JSONResponse> handleException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        exception.printStackTrace();
        log.warn(exception.getMessage());
        return ResponseHelper.badRequest("Unable to read HTTP Message");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONResponse> handleException(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();
        log.error(exception.getMessage());
        return ResponseHelper.internalError("Internal Server Error.");
    }

}
