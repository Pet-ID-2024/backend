package com.petid.domain.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomException extends RuntimeException {
    private ExceptionType exceptionType;
}
