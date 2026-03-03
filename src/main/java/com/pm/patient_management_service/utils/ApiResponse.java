package com.pm.patient_management_service.utils;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ApiResponse<T> {

    private final T data;
    private final String message;
    private final int statusCode;
    private final Instant timestamp;
    private final String uriPath;
    private final String error;
    private final int size;

    private ApiResponse(
            T data,
            String message,
            int statusCode,
            String uriPath,
            String error,
            int size
    ) {
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = Instant.now();
        this.uriPath = uriPath;
        this.error = error;
        this.size = size;
    }

    // success response
    public static <T> ApiResponse<T> success(
            T data,
            String message,
            int statusCode,
            String uriPath,
            int size
    ) {
        return new ApiResponse<>(
                data,
                message,
                statusCode,
                uriPath,
                null,
                size
        );
    }

    // error response
    public static <T> ApiResponse<T> error(
            String message,
            int statusCode,
            String uriPath,
            String error
    ) {
        return new ApiResponse<>(
                null,
                message,
                statusCode,
                uriPath,
                error,
                0
        );
    }
}