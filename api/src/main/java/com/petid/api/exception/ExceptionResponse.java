package com.petid.api.exception;

public record ExceptionResponse(
        long timestamp,
        int status,
        String error,
        String path
) {
    public static ExceptionResponse of(int code, String message, String path) {
        return new ExceptionResponse(
                System.currentTimeMillis(),
                code,
                message,
                path
        );
    }
}
