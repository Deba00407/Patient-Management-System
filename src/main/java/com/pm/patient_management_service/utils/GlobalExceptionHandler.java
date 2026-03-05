package com.pm.patient_management_service.utils;

import com.pm.patient_management_service.exceptions.EmailAlreadyExistsException;
import com.pm.patient_management_service.exceptions.PatientNotFoundException;
import com.pm.patient_management_service.exceptions.PhoneAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataBaseViolation(DataIntegrityViolationException ex) {

        ex.getMostSpecificCause();
        String message = ex.getMostSpecificCause().getMessage();

        logger.warn("Database constraint violation: {}", message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(message, HttpStatus.BAD_REQUEST.value(),
                        "/api/v1/patients/register", "DataBase Violation"));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        String message = ex.getMessage();

        logger.warn("Email already exists: {}", message);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(message, HttpStatus.CONFLICT.value(),
                        "/api/v1/patients/register", "Email Already Exists"));
    }

    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handlePhoneAlreadyExists(PhoneAlreadyExistsException ex) {
        String message = ex.getMessage();

        logger.warn("Phone already exists: {}", message);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(message, HttpStatus.CONFLICT.value(),
                        "/api/v1/patients/register", "Phone Already Exists"));
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handlePatientNotFound(PatientNotFoundException ex) {
        String message = ex.getMessage();

        logger.warn("Patient not found: {}", message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(message, HttpStatus.BAD_REQUEST.value(),
                        "/api/v1/patients/update", "Patient not found"));
    }
}