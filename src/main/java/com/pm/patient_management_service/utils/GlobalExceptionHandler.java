package com.pm.patient_management_service.utils;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataBaseViolation(String message){
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(message, HttpStatus.BAD_REQUEST.value(), "/api/v1/patients/register", "DataBase Violation"));
    }
}
