package com.petid.api.exception;

public record ExceptionResponse(
        int code,
        String message
) {
    public static ExceptionResponse of(int code, String message) {
        return new ExceptionResponse(
                code,
                message
        );
    }
}
